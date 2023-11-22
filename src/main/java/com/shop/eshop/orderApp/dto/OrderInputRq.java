package com.shop.eshop.orderApp.dto;

import com.shop.eshop.Obtain;
import com.shop.eshop.Payment;
import com.shop.eshop.Status;
import com.shop.eshop.customerApp.CustomerEntity;
import com.shop.eshop.orderListApp.OrderItemEntity;
import com.shop.eshop.productApp.ProductEntity;
import com.shop.eshop.productApp.dto.ProductQuantityInOrder;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderInputRq {

    private Long customer;
    private String city;
    private String address;
    private Obtain obtaining;
    private Payment payment;
    private List<ProductQuantityInOrder> products;


}
