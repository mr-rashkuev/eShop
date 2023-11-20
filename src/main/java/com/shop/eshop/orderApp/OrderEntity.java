package com.shop.eshop.orderApp;

import com.shop.eshop.customerApp.CustomerEntity;
import com.shop.eshop.Obtain;
import com.shop.eshop.Payment;
import com.shop.eshop.productApp.ProductEntity;
import com.shop.eshop.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "order")
@NoArgsConstructor

public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long orderId;
    @ManyToOne
    @JoinColumn(name = "customerId")
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
    private Status status;

    @OneToMany(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(
            name = "order_list",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<ProductEntity> productList;


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

//    @PrePersist
//    private void DateOfOrder() {
//        this.date = LocalDateTime.now();
//    }

//    @PostPersist
//    public void costCount() {
//        for (ProductEntity product : productList) {
//            cost += product.getPrice();
//        }
//    }
}
