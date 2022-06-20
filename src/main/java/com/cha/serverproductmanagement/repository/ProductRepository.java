package com.cha.serverproductmanagement.repository;

import com.cha.serverproductmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);

    @Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%',:query,'%') OR p.explanation LIKE CONCAT('%',:query,'%')")
    List<Product> searchProducts(String query);
}
