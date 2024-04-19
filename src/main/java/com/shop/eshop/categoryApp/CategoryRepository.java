package com.shop.eshop.categoryApp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(String name);
}
