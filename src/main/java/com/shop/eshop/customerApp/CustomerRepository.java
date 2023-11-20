package com.shop.eshop.customerApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
    @Query("from CustomerEntity where email =?1")
    Optional<CustomerEntity> getByEmail(String email);

    @Query("from CustomerEntity where phoneNumber=?1")
    Optional<CustomerEntity> getByPhoneNumber(String phoneNumber);
}
