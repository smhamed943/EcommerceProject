package com.example.ecommerceproject.services;

import com.example.ecommerceproject.DTOs.CreateProductRequestDto;
import com.example.ecommerceproject.DTOs.FakeStoreProductDto;
import com.example.ecommerceproject.Exceptions.ProductNotFoundException;
import com.example.ecommerceproject.models.Product;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
// to tell the spring that this is a service class
public class FakeStoreProductService implements ProductService{
    //using rest template, we call call 3rd party apis
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
    public Product getSingleProduct(long id) throws ProductNotFoundException {
        //here is where we call external api - 'https://fakestoreapi.com/products/1'
//        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
// we can now use response entity here
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);

        if (fakeStoreProductDtoResponseEntity.getStatusCode() != HttpStatusCode.valueOf(200)) {
        }
        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();
        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException("Product with id " + id + " is not present with the service.");
        }
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public Product createProduct(String title, String description,
                                 double price, String imageUrl, String category) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setImage(imageUrl);
        fakeStoreProductDto.setDescription(description);
        FakeStoreProductDto fakeStoreProductDto1 = restTemplate.postForObject("https://fakestoreapi.com/products" , fakeStoreProductDto, FakeStoreProductDto.class);
        return fakeStoreProductDto1.toProduct();
    }
}
