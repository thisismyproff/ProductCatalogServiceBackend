package com.mayur.ProductCatalogService.repos;

import com.mayur.ProductCatalogService.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
