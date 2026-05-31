package com.example.ecommerceproject.services;

import com.example.ecommerceproject.DTOs.CreateProductRequestDto;
import com.example.ecommerceproject.DTOs.FakeStoreProductDto;
import com.example.ecommerceproject.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
// to tell the spring that this is a service class
public class FakeStoreProductService implements ProductService{
    //using resttemplate, we call call 3rd party apis
    private RestTemplate restTemplate;

    //To inject the dependency in the service class, we add a constructor here
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product getSingleProduct(long id) {
        //here is where we call external api - 'https://fakestoreapi.com/products/1'
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);

        return fakeStoreProductDto.toProduct();
    }

    @Override
    public Product createProduct(CreateProductRequestDto createProductRequestDto) {
        return null;
    }
}
