package com.mayur.ProductCatalogService.dtos;

import com.mayur.ProductCatalogService.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDTO {
    private String name;
    private String description;
    private List<Product> products;
}
