package com.shop.eshop.orderListApp;

import com.shop.eshop.orderListApp.dto.OrderItemRs;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("/items")
@RestController
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping("/")
    private List<OrderItemRs> getAll(){
        return orderItemService.getAllItems();
    }
}
