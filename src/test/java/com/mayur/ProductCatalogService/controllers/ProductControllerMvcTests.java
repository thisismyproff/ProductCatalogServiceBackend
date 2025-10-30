package com.mayur.ProductCatalogService.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mayur.ProductCatalogService.coverters.ProductConverter;
import com.mayur.ProductCatalogService.dtos.ProductDTO;
import com.mayur.ProductCatalogService.models.Product;
import com.mayur.ProductCatalogService.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
                .andExpect(content().string(expectedJson))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    public void testCreateProduct_Success() throws Exception {
        Product product = new Product();
        product.setId(6L);
        product.setName("MackBook Pro");
        product.setDescription("Apple MackBook Pro 2023");
        product.setPrice(1999.99);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(6L);
        productDTO.setName("MackBook Pro");
        productDTO.setDescription("Apple MackBook Pro 2023");
        productDTO.setPrice(1999.99);

        when(productConverter.getProductFromDto(any(ProductDTO.class))).thenReturn(product);
        when(productService.createProduct(any(Product.class))).thenReturn(product);
        when(productConverter.convertToDto(any(Product.class))).thenReturn(productDTO);

        String requestJson = objectMapper.writeValueAsString(productDTO);
        String expectedJson = objectMapper.writeValueAsString(productDTO);

        mockMvc.perform(post("/products")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(expectedJson))
                .andExpect(jsonPath("$.id").value(6L))
                .andExpect(jsonPath("$.name").value("MackBook Pro"));
    }


}
