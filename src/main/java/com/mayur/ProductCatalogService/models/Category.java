package com.mayur.ProductCatalogService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category() {
        this.setCreatedAt(new java.util.Date());
        this.setLastUpdatedAt(new java.util.Date());
        this.setState(State.ACTIVE);
    }
}
