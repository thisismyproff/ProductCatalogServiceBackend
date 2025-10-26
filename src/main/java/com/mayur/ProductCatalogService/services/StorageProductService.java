package com.mayur.ProductCatalogService.services;

import com.mayur.ProductCatalogService.models.Product;
import com.mayur.ProductCatalogService.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Product> optional = productRepository.findById(product.getId());
        return optional.orElseGet(() -> productRepository.save(product));
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        return null;
    }
}
