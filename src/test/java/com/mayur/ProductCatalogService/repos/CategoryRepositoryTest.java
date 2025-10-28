package com.mayur.ProductCatalogService.repos;

import com.mayur.ProductCatalogService.models.Category;
import com.mayur.ProductCatalogService.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Transactional
    public void testFindById() {
        Long testId = 1L; // Example ID to test
        Category category = categoryRepository.findById(testId).isPresent() ? categoryRepository.findById(testId).get() : null;
        System.out.println(category);
        for (Product product : category.getProducts()) {
            System.out.println(product);
        }
    }

    @Test
    @Transactional
    public void testSomething() {
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            System.out.println(category.getName());
            for (Product product : category.getProducts()) {
                System.out.println(product.getName());
            }
        }
    }
}