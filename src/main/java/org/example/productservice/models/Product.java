package org.example.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.CascadeType;



@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    @ManyToOne (cascade= CascadeType.ALL)
    private Categories category;
    private String imageUrl;
    private Boolean isPublic;
    private int numberOfUnits;
}
