package com.shop.eshop.orderApp.dto;

import com.shop.eshop.Obtain;
import com.shop.eshop.Payment;
import com.shop.eshop.Status;
import com.shop.eshop.orderListApp.OrderItemEntity;
import com.shop.eshop.orderListApp.dto.OrderItemRs;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRs {
    private Long orderId;
    private String customer;
    private String city;
    private String address;
    private LocalDateTime date;
    private Obtain obtaining;
    private int cost;
    private Payment payment;
    private Status status;
    private List<OrderItemRs> orderItemList = new ArrayList<>();

}
