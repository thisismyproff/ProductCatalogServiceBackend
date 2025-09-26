package com.mayur.ProductCatalogService.services;

import com.mayur.ProductCatalogService.models.Product;

import java.util.List;

public interface IProductService {

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product updateProduct(Product product,Long id);

}
