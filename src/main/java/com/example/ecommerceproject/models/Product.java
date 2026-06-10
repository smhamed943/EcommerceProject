package com.example.ecommerceproject.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private double price;
//    private Category category;
    private String imageUrl;
}
