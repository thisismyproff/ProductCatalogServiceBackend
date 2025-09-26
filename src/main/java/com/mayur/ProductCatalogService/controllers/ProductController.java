package com.mayur.ProductCatalogService.controllers;

import com.mayur.ProductCatalogService.coverters.ProductConverter;
import com.mayur.ProductCatalogService.dtos.ProductDTO;
import com.mayur.ProductCatalogService.models.Product;
import com.mayur.ProductCatalogService.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private final IProductService iProductService;

    private final ProductConverter productConverter;

    @Autowired
    public ProductController(IProductService iProductService, ProductConverter productConverter) {
        this.iProductService = iProductService;
        this.productConverter = productConverter;
    }

    @GetMapping("/products/{id}")
    ResponseEntity<ProductDTO> getProductById(@PathVariable  Long id) {
            if (id <= 0) {
                throw new IllegalArgumentException("Please give id greater than zero");
            }
            Product product = iProductService.getProductById(id);
            if (product == null) {
                throw new RuntimeException("Product with id " + id + " not found");
            }
            ProductDTO productDTO = productConverter.convertToDto(product);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Custom-Header", "CustomHeaderValue");
            return new ResponseEntity<>(productDTO, headers, HttpStatus.OK);

    }

    @PutMapping("/products/{id}")
    ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Product product;
        product = iProductService.updateProduct(productConverter.getProductFromDto(productDTO), id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productConverter.convertToDto(product), HttpStatus.OK);
        }
    }

    @GetMapping("/products")
    List<ProductDTO> getAllProducts() {
        List<Product> products = iProductService.getAllProducts();
        if (!products.isEmpty()) {
            List<ProductDTO> productDTOS = new ArrayList<>();
            for (Product product : products) {
                productDTOS.add(productConverter.convertToDto(product));
            }
            return  productDTOS;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Products not found");
        }
    }


}
