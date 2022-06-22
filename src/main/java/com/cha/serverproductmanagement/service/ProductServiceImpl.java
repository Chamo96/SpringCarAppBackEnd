package com.cha.serverproductmanagement.service;

import com.cha.serverproductmanagement.model.Product;
import com.cha.serverproductmanagement.model.ProductPage;
import com.cha.serverproductmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findProductById(Long productId){

        return productRepository.findById(productId).orElse(null);
    }

    //pagination
    @Override
    public Page<Product> getProducts(ProductPage productPage){
        Sort sort = Sort.by(productPage.getSortDirection(), productPage.getSortBy());
        Pageable pageable = PageRequest.of(productPage.getPageNumber(), productPage.getPageSize(), sort);

        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Long numberOfProducts() {
        return productRepository.count();
    }

    @Override
    public List<Product> searchProducts(String query) {
        return productRepository.searchProducts(query);
    }
}
