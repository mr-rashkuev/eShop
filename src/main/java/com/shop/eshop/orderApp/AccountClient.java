package com.shop.eshop.orderApp;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

public interface AccountClient {

    @RequestLine("POST /accounts/")
    @Headers({"Content-Type: application/json"})
    void  tryToMakePayment(@RequestBody PaymentData paymentData);
}
