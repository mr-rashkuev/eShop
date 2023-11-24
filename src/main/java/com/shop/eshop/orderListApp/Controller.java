package com.shop.eshop.orderListApp;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("/items")
@RestController
@RequiredArgsConstructor
public class Controller {

    private final OrderItemService orderItemService;

    @GetMapping("/")
    private List<OrderItemEntity> getAll(){
        return orderItemService.getAllItems();
    }
}
