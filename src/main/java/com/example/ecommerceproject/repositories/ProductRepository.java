package com.example.ecommerceproject.repositories;

import com.example.ecommerceproject.Projections.ProductProjection;
import com.example.ecommerceproject.models.Category;
import com.example.ecommerceproject.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product p);

    @Override
    List<Product> findAll();//returns all products ie = select * from table

    @Override
    Optional<Product> findById(Long id);//Optional as the id we want may not be present in db
    
    List<Product> findByCategory(Category category);


    List<Product> findAllByCategory_Title(String categoryTitle);

    List<Product> findAllByCategory_Id(long categoryId);

    //HQL query
    @Query("select p.title as title, p.id as id from Product p where p.category.title = :categoryName")
    List<ProductProjection> getTitlesAndIdOfAllProductsWithGivenCategoryName(@Param("categoryName") String CategoryName);

    //we can write above code as native sql query
    @Query(value = "select * from product p where p.id =0 and p.title = :productTitle", nativeQuery = true )
    List<ProductProjection> getTitleAndIdOfAllProductsWithCategoryNameEquals(@Param("productTitle")String productTitle);
}
