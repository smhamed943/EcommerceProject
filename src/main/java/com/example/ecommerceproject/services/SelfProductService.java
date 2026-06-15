package com.example.ecommerceproject.services;

import com.example.ecommerceproject.Exceptions.ProductNotFoundException;
import com.example.ecommerceproject.controllers.ProductController;
import com.example.ecommerceproject.models.Category;
import com.example.ecommerceproject.models.Product;
import com.example.ecommerceproject.repositories.CategoryRepository;
import com.example.ecommerceproject.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    //inject category repo and product repo here
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    //inject this in constructor of
    public SelfProductService(CategoryRepository categoryRepository,
                              ProductRepository productRepository){
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();// makes an API call to db
    }

    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException("Product with id " + id + " does not exist. Please try with other id.");
        }
        return product.get(); //makes an API call to db
    }

    @Override
    public Product createProduct(String title, String description, double price,
                                 String imageUrl, String category) {
        Product p = new Product();
        p.setImageUrl(imageUrl);
        p.setPrice(price);
        p.setTitle(title);
        p.setDescription(description);

        Category categoryFromDb = categoryRepository.findByTitle(category);
        //if category is not present in db, then create category
        if(categoryFromDb == null){
            Category newCategory = new Category();
            newCategory.setTitle(title);

            categoryFromDb = newCategory;
        }
        p.setCategory(categoryFromDb);
        Product createdProduct = productRepository.save(p);
        return createdProduct;
    }
}
