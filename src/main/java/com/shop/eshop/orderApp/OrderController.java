package com.shop.eshop.orderApp;

import com.shop.eshop.orderApp.dto.OrderInputRq;
import com.shop.eshop.orderApp.dto.OrderRs;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/")
    public void registerOrder(@RequestBody OrderInputRq orderInputRq) {
        orderService.registerOrder(orderInputRq);
    }

    @GetMapping("/")
    public List<OrderRs> getAllOrders() {
        return orderService.getAllOrders();
    }
}
