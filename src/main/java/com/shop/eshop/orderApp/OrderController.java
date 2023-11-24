package com.shop.eshop.orderApp;

import com.shop.eshop.orderApp.dto.OrderInputRq;
import com.shop.eshop.orderApp.dto.OrderOutputRq;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public List<OrderOutputRq> getAllOrders() {
        return orderService.getAllOrders();
    }
}
