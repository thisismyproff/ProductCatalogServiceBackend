package com.mayur.ProductCatalogService.clients;

import com.mayur.ProductCatalogService.coverters.ProductConverter;
import com.mayur.ProductCatalogService.fakestoredtos.FakeStoreProductDto;
import com.mayur.ProductCatalogService.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class FakeStoreAPIClient {
    private final RestTemplateBuilder restTemplateBuilder;
    private final ProductConverter productConverter;

    @Autowired
    public FakeStoreAPIClient(RestTemplateBuilder restTemplateBuilder, ProductConverter productConverter) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.productConverter = productConverter;
    }

    public FakeStoreProductDto getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                requestForEntity(HttpMethod.GET,"https://fakestoreapi.com/products/{id}",null,
                        FakeStoreProductDto.class, id);
        if (validateResponse(fakeStoreProductDtoResponseEntity)) {
            return fakeStoreProductDtoResponseEntity.getBody();
        }
        return null;
    }

    public FakeStoreProductDto[] getAllProducts() {
        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDtoResponseEntity =
                requestForEntity(HttpMethod.GET,"https://fakestoreapi.com/products",null, FakeStoreProductDto[].class);
        if (validateResponse(fakeStoreProductDtoResponseEntity)) {
            return fakeStoreProductDtoResponseEntity.getBody();
        }
        return null;
    }

    public FakeStoreProductDto updateProduct(Product product,Long id) {
        FakeStoreProductDto fakeStoreProductDto;
        fakeStoreProductDto = productConverter.convertToFakeStoreProductDto(product);
        fakeStoreProductDto.setId(id);
        ResponseEntity<FakeStoreProductDto> responseEntity =
                requestForEntity(HttpMethod.PUT,"https://fakestoreapi.com/products/{id}", fakeStoreProductDto, FakeStoreProductDto.class, id);
        if (validateResponse(responseEntity)) {
            return responseEntity.getBody();
        }
        return null;
    }


    private boolean validateResponse(ResponseEntity<?> responseEntity) {
        return responseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200)) &&
                responseEntity.getBody() != null;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

}
