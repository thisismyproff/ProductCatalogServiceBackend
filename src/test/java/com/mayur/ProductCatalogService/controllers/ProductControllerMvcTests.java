package com.mayur.ProductCatalogService.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mayur.ProductCatalogService.coverters.ProductConverter;
import com.mayur.ProductCatalogService.dtos.ProductDTO;
import com.mayur.ProductCatalogService.models.Product;
import com.mayur.ProductCatalogService.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTests {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IProductService productService;

    @MockitoBean
    private ProductConverter productConverter;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetAllProducts_Success() throws Exception {

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("MackBook Pro");
        product1.setDescription("Apple MackBook Pro 2023");
        product1.setPrice(1999.99);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Dell XPS 13");
        product2.setDescription("Dell XPS 13 2023");
        product2.setPrice(1499.99);

        List<Product> products = List.of(product1, product2);
        when(productService.getAllProducts()).thenReturn(products);

        // Arrange DTOs returned by converter
        ProductDTO dto1 = new ProductDTO();
        dto1.setId(1L);
        dto1.setName("MackBook Pro");
        dto1.setDescription("Apple MackBook Pro 2023");
        dto1.setPrice(1999.99);

        ProductDTO dto2 = new ProductDTO();
        dto2.setId(2L);
        dto2.setName("Dell XPS 13");
        dto2.setDescription("Dell XPS 13 2023");
        dto2.setPrice(1499.99);

        when(productConverter.convertToDto(product1)).thenReturn(dto1);
        when(productConverter.convertToDto(product2)).thenReturn(dto2);

        String expectedJson = objectMapper.writeValueAsString(List.of(dto1, dto2));

        // Act & Assert
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

}
