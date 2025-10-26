package com.mayur.ProductCatalogService.repos;

import com.mayur.ProductCatalogService.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product product);

    Optional<Product> findById(long id);

    @Override
    List<Product> findAll();

    List<Product> findProductByPriceBetween(Double low, Double high);


    List<Product> findProductsByIsPrime(Boolean isPrime);

    List<Product> findProductsByOrderByPriceAsc();

    @Query("select p.description from Product p where p.id = :id ")
    String findProoductDescrriptionById(Long id);
}
