package com.cha.serverproductmanagement.service;

import com.cha.serverproductmanagement.model.Product;

import java.util.List;

public interface ProductService {
    Product findProductById(Long productId);

    List<Product> findAllProducts();

    Long numberOfProducts();

    List<Product> searchProducts(String query);
}
