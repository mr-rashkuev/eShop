package com.shop.eshop.orderApp;

import com.shop.eshop.orderApp.dto.OrderInputRq;
import com.shop.eshop.orderApp.dto.OrderRs;
import com.shop.eshop.orderListApp.OrderItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderStatisticsService orderStatisticsService;

    @PostMapping("/")
    public void registerOrder(@RequestBody OrderInputRq orderInputRq) {
        orderService.registerOrder(orderInputRq);
    }

    @GetMapping("/")
    public List<OrderRs> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{customerId}")
    public List<OrderRs> findOrdersByCustomer(@PathVariable Long customerId) {
        return orderService.findOrdersByCustomer(customerId);
    }

    @PutMapping("/cancel/{id}")
    public void cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
    }

    @GetMapping("/productSell/")
    public List<OrderItemEntity> getMostSells() {
        return orderStatisticsService.getMostSells();
    }
}
