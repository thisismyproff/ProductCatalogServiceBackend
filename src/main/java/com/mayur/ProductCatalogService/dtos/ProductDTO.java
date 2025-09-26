package com.mayur.ProductCatalogService.dtos;


import com.mayur.ProductCatalogService.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private CategoryDTO categoryDTO;
}
