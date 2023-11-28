package com.shop.eshop.productApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("from ProductEntity where id in :ids")
    List<Optional<ProductEntity>> findByProductIds(List<Long> ids);

}
