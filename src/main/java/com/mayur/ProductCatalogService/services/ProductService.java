package com.mayur.ProductCatalogService.services;

import com.mayur.ProductCatalogService.clients.FakeStoreAPIClient;
import com.mayur.ProductCatalogService.coverters.ProductConverter;
import com.mayur.ProductCatalogService.fakestoredtos.FakeStoreProductDto;
import com.mayur.ProductCatalogService.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private final ProductConverter productConverter;
    private final FakeStoreAPIClient fakeStoreAPIClient;

    @Autowired
    public ProductService(ProductConverter productConverter,
                          FakeStoreAPIClient fakeStoreAPIClient) {
        this.fakeStoreAPIClient = fakeStoreAPIClient;
        this.productConverter = productConverter;
    }

    @Override
    public Product getProductById(Long id) {
        Product product = null;
        if(null !=fakeStoreAPIClient.getProductById(id) ) {
            product = productConverter.getProductFromDto(fakeStoreAPIClient.getProductById(id));
        }
        return  product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        FakeStoreProductDto[] fakeStoreProductDtos = fakeStoreAPIClient.getAllProducts();
        if (fakeStoreProductDtos == null) {
            return products;
        }
        for (FakeStoreProductDto productDto : fakeStoreProductDtos) {
            products.add(productConverter.getProductFromDto(productDto));
        }
        return products;
    }

    @Override
    public Product updateProduct(Product product,Long id) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreAPIClient.updateProduct(product,id);
        if (null != fakeStoreProductDto) {
            return productConverter.getProductFromDto(fakeStoreProductDto);
        }
        return null;
    }
    
    @Override
    public Product createProduct(Product product) {
        return null;
    }


}
