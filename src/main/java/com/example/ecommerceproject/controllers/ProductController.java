package com.example.ecommerceproject.controllers;

import com.example.ecommerceproject.DTOs.CreateProductRequestDto;
import com.example.ecommerceproject.DTOs.FakeStoreProductDto;
import com.example.ecommerceproject.models.Product;
import com.example.ecommerceproject.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    //creating instance of productservice here to call methods included there
    public ProductService productService;
    //inject the dependencies as below
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    //to create a product
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
        return productService.createProduct(createProductRequestDto);
    }
        //to get all products
        @GetMapping("/products")
        public List<Product> getAllProducts () {
            return productService.getAllProducts();
        }
    //to get a single product
    @GetMapping("/products/{id}")
    public Product getSingleProduct(@PathVariable ("id") long id){
        return productService.getSingleProduct(id);
    }
    }
