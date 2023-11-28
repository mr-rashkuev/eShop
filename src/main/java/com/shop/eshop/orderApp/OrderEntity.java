package com.shop.eshop.orderApp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shop.eshop.customerApp.CustomerEntity;
import com.shop.eshop.Obtain;
import com.shop.eshop.Payment;

import com.shop.eshop.orderListApp.OrderItemEntity;
import com.shop.eshop.orderListApp.OrderItemEntity.OrderItemPK;
import com.shop.eshop.productApp.ProductEntity;
import com.shop.eshop.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "order_info")
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long orderId;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
    @Column(name = "city")
    private String city;
    @Column(name = "address")
    private String address;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "obtaining")
    @Enumerated(EnumType.STRING)
    private Obtain obtaining;
    @Column(name = "cost")
    private int cost;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment")
    private Payment payment;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany
    private List<OrderItemEntity> productList;

    public OrderEntity(CustomerEntity customer, String city, String address, Obtain obtaining, int cost, Payment payment, Status status) {
        this.customer = customer;
        this.city = city;
        this.address = address;
        this.obtaining = obtaining;
        this.cost = cost;
        this.payment = payment;
        this.status = status;
    }

    public OrderEntity(String city, String address, Obtain obtaining, Payment payment) {
        this.city = city;
        this.address = address;
        this.obtaining = obtaining;
        this.payment = payment;

    }

    @PrePersist
    private void setDate() {
        this.date = LocalDateTime.now();
    }

    public void addProductToOrder(OrderItemEntity orderItem){
        if (productList==null){
            productList = new ArrayList<>();
        }
        productList.add(orderItem);
    }
}
