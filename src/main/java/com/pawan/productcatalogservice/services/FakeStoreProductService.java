package com.pawan.productcatalogservice.services;

import com.pawan.productcatalogservice.dtos.FakestoreProductDto;
import com.pawan.productcatalogservice.models.Product;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements IProductService{

    private RestTemplate restTemplate;

    private FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {
        ResponseEntity<FakestoreProductDto> fakestoreProductDtoResponseEntity=
                restTemplate.getForEntity(
                        "https://fakestoreapi.com/products/{id}",
                        FakestoreProductDto.class,
                        id
                );

        if(fakestoreProductDtoResponseEntity.hasBody() &&
        fakestoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))){
            return fakestoreProductDtoResponseEntity.getBody().
                    from(fakestoreProductDtoResponseEntity.getBody());
        }

        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product createProduct(Product input) {
        return null;
    }
}
