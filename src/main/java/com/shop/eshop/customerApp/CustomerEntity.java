package com.shop.eshop.customerApp;

import com.shop.eshop.orderApp.OrderEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "city")
    private String city;
    @Column(name = "budget")
    private int budget;

    @OneToMany(mappedBy = "customer")
    private List<OrderEntity> orderList;

    public CustomerEntity(String firstName, String middleName, String lastName, String phoneNumber, String email, String city, int budget) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.city = city;
        this.budget = budget;
    }

    public CustomerEntity() {

    }
}
