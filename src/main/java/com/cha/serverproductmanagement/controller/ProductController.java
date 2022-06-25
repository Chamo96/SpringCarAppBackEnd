package com.cha.serverproductmanagement.controller;

import com.cha.serverproductmanagement.model.ApiResponse;
import com.cha.serverproductmanagement.model.Product;
import com.cha.serverproductmanagement.model.ProductPage;
import com.cha.serverproductmanagement.model.StringResponse;
import com.cha.serverproductmanagement.repository.ProductRepository;
import com.cha.serverproductmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

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

    //Pagination sorting and pagination
    @GetMapping("/api/discover/products")
    public ResponseEntity<Page<Product>> getProducts(ProductPage productPage) {
        return new ResponseEntity<>(productService.getProducts(productPage), HttpStatus.OK);
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

    //Get search with pagination (Repo and controller)
    @GetMapping("/api/discover/new/products")
    public Page<Product> getProductsWitSearch(@RequestParam Optional<String> query,
                                              @RequestParam Optional<Integer> pageNumber, ProductPage productPage) {

        Sort sort = Sort.by(productPage.getSortDirection(), productPage.getSortBy());
        Pageable pageable = PageRequest.of(pageNumber.orElse(0), 6, sort);
        return productRepository.searchProductsWithPagination(query.orElse("_"), pageable);
    }
}
