package com.mayur.ProductCatalogService.controllers;

import com.mayur.ProductCatalogService.dtos.ProductDTO;
import com.mayur.ProductCatalogService.models.Product;
import com.mayur.ProductCatalogService.services.IProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockitoBean
    private IProductService iProductService;

    @Test
    void testGetProductById_withValidId() {
        //Arrange
        Long productId = 1L;

        Product product = new Product();
        product.setId(productId);
        product.setName("iPhone 13");
        product.setDescription("Latest Apple iPhone 13");
        product.setPrice(999.99);

        when(iProductService.getProductById(productId)).thenReturn(product);

        //Act
        ResponseEntity<ProductDTO> productDTOResponseEntity = productController.getProductById(productId);
        //Assert
        assertNotNull(productDTOResponseEntity);
        assertNotNull(productDTOResponseEntity.getBody());
        assertEquals(productId, productDTOResponseEntity.getBody().getId());
        assertEquals("iPhone 13", productDTOResponseEntity.getBody().getName());
        assertEquals("Latest Apple iPhone 13", productDTOResponseEntity.getBody().getDescription());
        assertEquals(999.99, productDTOResponseEntity.getBody().getPrice());

    }


    @Test
    void updateProduct() {
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void createProduct() {
    }
}