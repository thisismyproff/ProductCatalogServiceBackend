package com.mayur.ProductCatalogService.fakestoredtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private Long price;
    private String description;
    private String category;
    private String image;
}
