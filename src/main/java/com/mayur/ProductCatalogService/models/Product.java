package com.mayur.ProductCatalogService.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Product extends  BaseModel {
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private Category category;

    //Business Logic
    private boolean isPrime;


    public Product() {
        this.setCreatedAt(new Date());
        this.setLastUpdatedAt(new Date());
        this.setState(State.ACTIVE);
    }
}
