package com.example.ecommerceproject;

import com.example.ecommerceproject.models.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceProjectApplication.class, args);

        Product p1 = new Product();
        p1.getId();

	}

}
