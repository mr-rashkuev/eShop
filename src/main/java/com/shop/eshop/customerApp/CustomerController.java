package com.shop.eshop.customerApp;

import com.shop.eshop.customerApp.dto.CustomerRq;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/")
    public List<CustomerRq> showAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping("/")
    public void registerCustomer(@RequestBody CustomerRq customerRq) {
        customerService.registerCustomer(customerRq);
    }
    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable Long id, @RequestBody CustomerRq customer) {
        customerService.updateCustomer(id, customer);
    }

}
