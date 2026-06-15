package com.example.ecommerceproject;

import com.example.ecommerceproject.Projections.ProductProjection;
import com.example.ecommerceproject.models.Product;
import com.example.ecommerceproject.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EcommerceProjectApplicationTests {
	@Autowired
	ProductRepository productRepository;// to inject the constructor

	@Test
	void contextLoads() {
	}

	@Test
	void testingQueries() {
		List<Product> products = productRepository.findAllByCategory_Title("electronics");
		System.out.println(products);

		List<Product> products1 = productRepository.getTitlesAndIdOfAllProductsWithTheGivenCategoryName("electronics");
		System.out.println(products1);

		List<ProductProjection> productProjections = productRepository.getTitlesAndIdOfAllProductsWithGivenCategoryName("electronics");
		//System.out.println(productProjections);

		for (ProductProjection productProjection : productProjections) {
			System.out.println(productProjection.getId());
			System.out.println(productProjection.getTitle());
		}
		System.out.println();
	}

}
