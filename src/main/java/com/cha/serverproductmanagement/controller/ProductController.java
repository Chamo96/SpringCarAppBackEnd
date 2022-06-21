package com.cha.serverproductmanagement.controller;

import com.cha.serverproductmanagement.model.ApiResponse;
import com.cha.serverproductmanagement.model.Product;
import com.cha.serverproductmanagement.model.StringResponse;
import com.cha.serverproductmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/api/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productService.findProductById(id), HttpStatus.OK);
    }


    @GetMapping("/api/products")
    public ResponseEntity<?> getAllProducts() {
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }

    //get products with totalItem and response
    @GetMapping("/api/product-all")
    public ApiResponse<List<Product>> findAllProducts() {
        List<Product> allProducts = productService.findAllProducts();

        return new ApiResponse<>(allProducts.size(), allProducts);
    }

    @GetMapping("/api/products/search")
    public ResponseEntity<?> searchProducts(@RequestParam("query") String query) {
        return new ResponseEntity<>(productService.searchProducts(query), HttpStatus.OK);
    }

    @GetMapping("/api/product-number")
    public ResponseEntity<?> numberOfProducts() {
        Long number = productService.numberOfProducts();
        StringResponse response = new StringResponse();
        response.setResponse(number.toString());

        return new ResponseEntity<>(number, HttpStatus.OK);
    }
}
