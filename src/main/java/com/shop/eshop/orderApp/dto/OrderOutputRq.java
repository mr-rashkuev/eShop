package com.shop.eshop.orderApp.dto;

import com.shop.eshop.Obtain;
import com.shop.eshop.Payment;
import com.shop.eshop.Status;
import com.shop.eshop.customerApp.CustomerEntity;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class OrderOutputRq {
    private Long orderId;
    private String customer;
    private String city;
    private String address;
    private LocalDateTime date;
    private Obtain obtaining;
    private int cost;
    private Payment payment;
    private Status status;

}
