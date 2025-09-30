package com.mayur.ProductCatalogService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category extends BaseModel{
    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
