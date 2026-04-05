package com.pawan.productcatalogservice.services;

import com.pawan.productcatalogservice.dtos.FakestoreProductDto;
import com.pawan.productcatalogservice.models.Product;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService{

    private RestTemplate restTemplate;

    private FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    //recreating putForEntity method referenced from deprecated method
    public <T> ResponseEntity<T>  putForEntity(String url, @Nullable Object request,
                                               Class<T> responseType, Object... uriVariables)
            throws RestClientException{
        RequestCallback requestCallback=restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor= restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.PUT,requestCallback, responseExtractor, uriVariables);
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
        List<Product> products = new ArrayList<>();
        ResponseEntity<FakestoreProductDto[]> response = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                FakestoreProductDto[].class
        );
        if(response.hasBody() && response.getStatusCode().equals(HttpStatusCode.valueOf(200))){
            FakestoreProductDto[] fakestoreProductDtos = response.getBody();

            for(FakestoreProductDto fakestoreProductDto: fakestoreProductDtos){
                products.add(fakestoreProductDto.from(fakestoreProductDto));
            }

            return products;
        }
        return null;
    }

    @Override
    public Product createProduct(Product newProduct) {
        FakestoreProductDto fakestoreProductDto = newProduct.toFakestoreProductDto();
        ResponseEntity<FakestoreProductDto> response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fakestoreProductDto,
                ResponseEntity.class
        );
        if(!response.hasBody() && response.getStatusCode().equals(HttpStatusCode.valueOf(201)) ){
            return fakestoreProductDto.from(response.getBody());
        }
        return null;
    }

    @Override
    public Product replaceProduct(Product prod, Long productId) {
        FakestoreProductDto fakestoreProductDto= prod.toFakestoreProductDto();
        ResponseEntity<FakestoreProductDto> response = this.putForEntity(
                "https://fakestoreapi.com/products/{id}",
                fakestoreProductDto,
                FakestoreProductDto.class,
                productId
        );

        if(response.hasBody() && response.getStatusCode().equals(HttpStatusCode.valueOf(200))){
            return prod;
        }
        return null;
    }
}
