package com.pawan.productcatalogservice.services;

import com.pawan.productcatalogservice.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product createProduct(Product input);
}
