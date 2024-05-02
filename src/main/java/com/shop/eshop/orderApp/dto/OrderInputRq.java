package com.shop.eshop.orderApp.dto;

import com.shop.eshop.Obtain;
import com.shop.eshop.Payment;
import com.shop.eshop.productApp.dto.ProductDetails;
import lombok.*;

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
    private List<ProductDetails> products;
    private String cardNumber;
}
