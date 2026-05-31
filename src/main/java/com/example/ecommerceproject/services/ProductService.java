package com.example.ecommerceproject.services;

import com.example.ecommerceproject.DTOs.CreateProductRequestDto;
import com.example.ecommerceproject.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getSingleProduct(long id);
    Product createProduct(CreateProductRequestDto createProductRequestDto);
}
