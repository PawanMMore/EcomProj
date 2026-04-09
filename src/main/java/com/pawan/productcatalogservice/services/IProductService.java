package com.pawan.productcatalogservice.services;

import com.pawan.productcatalogservice.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long productId);
    List<Product> getAllProducts();
    Product createProduct(Product inputProduct);
    Product replaceProduct(Product prod, Long productId);
}
