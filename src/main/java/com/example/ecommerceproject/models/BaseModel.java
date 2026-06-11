package com.example.ecommerceproject.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    private long id;
    private Date lastModifiedAt;
    private Date createdAt;
    private boolean isDeleted;
}
