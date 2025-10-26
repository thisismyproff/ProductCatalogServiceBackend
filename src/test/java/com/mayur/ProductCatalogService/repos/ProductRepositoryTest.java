package com.mayur.ProductCatalogService.repos;

import com.mayur.ProductCatalogService.ProductCatalogServiceApplication;
import com.mayur.ProductCatalogService.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;


    @Test
    void testQueries() {
//        List<Product> products = productRepository.findProductByPriceBetween(1D,90000D);
//        System.out.println(products.size() + " products found");
//        System.out.println(products.get(0).getId());
//
//        List<Product> productsNotPrime = productRepository.findProductsByIsPrime(false);
//        System.out.println(productsNotPrime.size() + " products found");
//        System.out.println(productsNotPrime.get(0).getId());

        List<Product> products = productRepository.findProductsByOrderByPriceAsc();
        System.out.println(products.size() + " products found");
        System.out.println(products.get(0).getPrice());



    }
}