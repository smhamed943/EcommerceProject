package com.example.ecommerceproject.controllers;

import com.example.ecommerceproject.DTOs.CreateProductRequestDto;
import com.example.ecommerceproject.DTOs.ErrorDTO;
import com.example.ecommerceproject.DTOs.FakeStoreProductDto;
import com.example.ecommerceproject.Exceptions.ProductNotFoundException;
import com.example.ecommerceproject.models.Product;
import com.example.ecommerceproject.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    private final RestTemplate restTemplate;
    //creating instance of product service here to call methods included there
    public ProductService productService;

    //inject the dependencies as below
    public ProductController(ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    //to create a product
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
        return productService.createProduct(createProductRequestDto.getTitle(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getPrice(), createProductRequestDto.getImage(),
                createProductRequestDto.getCategory());
    }

    //to get all products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        //to return getAllProducts from fakestore, we can use restTemplate here or product service as the case maybe
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        //We need to convert the arraylist of fakestore products to fakestore products
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            Product p = fakeStoreProductDto.toProduct();
        products.add(p);
    }
        return products;
}
    //to get a single product
    //also to send the response code along with the response, we can make changes below
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable ("id") long id) throws ProductNotFoundException {
        Product p = productService.getSingleProduct(id);
        ResponseEntity<Product> responseEntity;
        //if product is not present, we can handle exception here
        if(p==null){
            responseEntity = new ResponseEntity<>(p, HttpStatus.NOT_FOUND);
        }
        else{
            responseEntity = new ResponseEntity<>(p, HttpStatus.OK);
        }
        return responseEntity;
    }
    //for exception handling, along with incoming data, we can add extra information too
    //commenting out the below code to add advice in the ProductController class
//        @ExceptionHandler(ProductNotFoundException.class)
//        public ResponseEntity<ErrorDTO> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
//            ErrorDTO errorDTO = new ErrorDTO();
//            errorDTO.setMessage(productNotFoundException.getMessage());
//
//            ResponseEntity<ErrorDTO> responseEntity = new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
//            return responseEntity;
//    }
}
