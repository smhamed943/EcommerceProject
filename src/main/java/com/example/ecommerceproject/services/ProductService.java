package com.example.ecommerceproject.services;

import com.example.ecommerceproject.DTOs.CreateProductRequestDto;
import com.example.ecommerceproject.Exceptions.ProductNotFoundException;
import com.example.ecommerceproject.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getSingleProduct(long id) throws ProductNotFoundException;
    Product createProduct(String title, String description, double price, String imageUrl, String category);
}
