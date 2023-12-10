package com.shop.eshop.orderApp;

import com.shop.eshop.orderApp.dto.OrderInputRq;
import com.shop.eshop.orderApp.dto.OrderRs;
import com.shop.eshop.orderListApp.OrderItemEntity;
import com.shop.eshop.orderListApp.dto.MostSells;
import com.shop.eshop.orderListApp.dto.OrderItemRs;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public List<MostSells> getMostSells() {
        return orderStatisticsService.findMostSells();
    }

    @GetMapping("/productMost/")
    public List<MostSells> getMost() {
        return orderStatisticsService.getMostSells();
    }

    @GetMapping("/period/")
    public List<OrderItemRs> getByPeriod(@RequestParam(name = "low", required = false) @DateTimeFormat(pattern="yyyyMMdd") LocalDate low,
                                         @RequestParam(name = "high", required = false) @DateTimeFormat(pattern="yyyyMMdd") LocalDate high) {
        return orderStatisticsService.getByPeriod(low.atStartOfDay(), high.atStartOfDay());
    }
}
