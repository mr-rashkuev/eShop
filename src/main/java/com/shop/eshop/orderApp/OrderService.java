package com.shop.eshop.orderApp;

import com.shop.eshop.Status;
import com.shop.eshop.customerApp.BusinessException;
import com.shop.eshop.customerApp.CustomerEntity;
import com.shop.eshop.customerApp.CustomerRepository;
import com.shop.eshop.orderApp.dto.OrderInputRq;
import com.shop.eshop.orderApp.dto.OrderRs;
import com.shop.eshop.orderApp.mapper.OrderMapper;
import com.shop.eshop.orderListApp.OrderItemEntity;
import com.shop.eshop.orderListApp.OrderItemRepository;
import com.shop.eshop.productApp.ProductEntity;
import com.shop.eshop.productApp.ProductRepository;
import com.shop.eshop.productApp.dto.ProductAndQuantity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public List<OrderRs> findOrdersByCustomer(Long customerId) {
        CustomerEntity customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new BusinessException("Нет пользователя с ID " + customerId));
        return orderRepository.findOrdersByCustomer(customer)
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<OrderRs> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void cancelOrder(Long id) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Заказ с ID " + id + " не найден"));
        if (order.getStatus() == Status.RESERVED) {
            order.setStatus(Status.CANCELED);
            Map<ProductEntity, OrderItemEntity> mapOrder = orderItemRepository.findAllByOrderId(order.getOrderId())
                    .stream()
                    .collect(Collectors.toMap(OrderItemEntity::getProduct, Function.identity()));
            for (Map.Entry<ProductEntity, OrderItemEntity> entry: mapOrder.entrySet()) {
                int quantityToReturn = entry.getKey().getQuantity() + entry.getValue().getQuantity();
                entry.getKey().setQuantity(quantityToReturn);
            }
        } else {
            throw new BusinessException("Слишком поздно...");
        }
    }

    @Transactional
    public void registerOrder(OrderInputRq orderInputRq) {
        OrderEntity order = orderMapper.toEntity(orderInputRq);
        CustomerEntity customer = customerRepository.findById(orderInputRq.getCustomer())
                .orElseThrow(() -> new BusinessException("Данный покупатель не зарегистрирован в системе"));
        order.setCustomer(customer);
        order.setStatus(Status.RESERVED);
        orderRepository.save(order);
        checkAndAddProductToOrder(order, orderInputRq.getProducts());
    }

    private void checkAndAddProductToOrder(OrderEntity order, List<ProductAndQuantity> productAndQuantities) {
        List<Long> productIdList = productAndQuantities
                .stream()
                .map(ProductAndQuantity::getProductId)
                .collect(Collectors.toList());
        List<ProductEntity> productEntityList = productRepository.findByProductIds(productIdList)
                .stream()
                .map(productEntity -> productEntity.orElseThrow(() -> new BusinessException("Товар не найден")))
                .collect(Collectors.toList());
        int costOfOrder = 0;
        for (ProductAndQuantity item : productAndQuantities) {
            for (ProductEntity product : productEntityList) {
                if (Objects.equals(item.getProductId(), product.getId())) {
                    int productQuantityLeft = product.getQuantity() - item.getQuantity();
                    if (productQuantityLeft >= 0) {
                        createOrderItem(order, product, item.getQuantity());
                        product.setQuantity(productQuantityLeft);
                        costOfOrder += product.getPrice() * item.getQuantity();
                    } else {
                        throw new BusinessException("Недостаточно товара на складе");
                    }
                }
            }
        }
        order.setCost(costOfOrder);
    }

    private void createOrderItem(OrderEntity order, ProductEntity product, int quantity) {
        OrderItemEntity orderItem = new OrderItemEntity(
                order.getOrderId(), product.getId(), quantity);
        orderItemRepository.save(orderItem);
    }
}
