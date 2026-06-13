package com.example.ecommerceproject.repositories;

import com.example.ecommerceproject.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByTitle(String title);
    //internally jpa will do select * from Category where title like 'title'
    //and convert it into category object and returns it
}
