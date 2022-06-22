package com.cha.serverproductmanagement.service;

import com.cha.serverproductmanagement.model.Product;
import com.cha.serverproductmanagement.model.ProductPage;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product findProductById(Long productId);

    //pagination
    Page<Product> getProducts(ProductPage productPage);

    List<Product> findAllProducts();

    Long numberOfProducts();

    List<Product> searchProducts(String query);
}
