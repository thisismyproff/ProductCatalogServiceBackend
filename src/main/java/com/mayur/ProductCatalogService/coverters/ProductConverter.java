package com.mayur.ProductCatalogService.coverters;

import com.mayur.ProductCatalogService.dtos.CategoryDTO;
import com.mayur.ProductCatalogService.dtos.ProductDTO;
import com.mayur.ProductCatalogService.fakestoredtos.FakeStoreProductDto;
import com.mayur.ProductCatalogService.models.Category;
import com.mayur.ProductCatalogService.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public ProductDTO convertToDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageUrl(product.getImageUrl());
        productDTO.setPrice(product.getPrice());
        if (product.getCategory() != null) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setName(product.getCategory().getName());
            categoryDTO.setDescription(product.getCategory().getDescription());
            productDTO.setCategoryDTO(categoryDTO);
        }
        return productDTO;
    }
    public Product getProductFromDto(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setPrice(Double.valueOf(fakeStoreProductDto.getPrice()));
        product.setDescription(fakeStoreProductDto.getDescription());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(fakeStoreProductDto.getImage());
        return product;
    }
    public FakeStoreProductDto convertToFakeStoreProductDto(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setPrice((long) product.getPrice().floatValue());
        fakeStoreProductDto.setDescription(product.getDescription());
        if (product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        fakeStoreProductDto.setImage(product.getImageUrl());
        return fakeStoreProductDto;
    }
    public Product getProductFromDto(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setImageUrl(productDTO.getImageUrl());
        product.setPrice(productDTO.getPrice());
        if (productDTO.getCategoryDTO() != null) {
            Category category = new Category();
            category.setName(productDTO.getCategoryDTO().getName());
            category.setDescription(productDTO.getCategoryDTO().getDescription());
            product.setCategory(category);
        }
        return product;
    }

}
