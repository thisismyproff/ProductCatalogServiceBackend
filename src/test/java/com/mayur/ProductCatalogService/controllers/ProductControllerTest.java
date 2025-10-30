package com.mayur.ProductCatalogService.controllers;

import com.mayur.ProductCatalogService.dtos.ProductDTO;
import com.mayur.ProductCatalogService.models.Product;
import com.mayur.ProductCatalogService.services.IProductService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockitoBean
    private IProductService iProductService;

    @Captor
    private ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);

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

        verify(iProductService,times(1)).getProductById(productId);


    }


    @Test
    void testGetProductById_withInvalidId() {
        //Arrange
        Long productId = -1L;
        //Assert
        Exception exception = assertThrows(IllegalArgumentException.class,()-> productController.getProductById(productId));

        assertEquals("Please give id greater than zero", exception.getMessage());

        verify(iProductService,times(0)).getProductById(productId);


    }

    @Test
    void testGetProductById_withRuntimeException() {
        //Arrange
        Long productId = 20000L;

        when(iProductService.getProductById(productId)).thenThrow(new RuntimeException("Product with id " + productId + " not found"));

        Exception e =assertThrows(RuntimeException.class, () -> productController.getProductById(productId));
        assertEquals("Product with id " + productId + " not found", e.getMessage());


    }

    @Test
    void testGetProductById_calledWithSameArgument() {
        //Arrange
        Long productId = 5L;

        Product product = new Product();
        product.setId(productId);
        product.setName("iPhone 13");
        product.setDescription("Latest Apple iPhone 13");
        product.setPrice(999.99);

        when(iProductService.getProductById(productId)).thenReturn(product);

        //Act
        productController.getProductById(productId);

        //Assert
        verify(iProductService).getProductById(idCaptor.capture());
        Long capturedId = idCaptor.getValue();
        assertEquals(productId, capturedId);

    }
}