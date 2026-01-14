package com.pawan.productcatalogservice.controllers;

import com.pawan.productcatalogservice.dtos.ProductReponseDTO;
import com.pawan.productcatalogservice.dtos.ProductRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @PostMapping("/newProduct")
    ProductReponseDTO createProduct(@RequestBody ProductRequestDTO productRequestDTO){
        ProductReponseDTO productReponseDTO = new ProductReponseDTO();
        return  productReponseDTO;
    }

    @GetMapping("/products/{id}")
    ProductReponseDTO getProductByProductId(@PathVariable("id") Long id){
        ProductReponseDTO productReponseDTO = new ProductReponseDTO();
        return productReponseDTO;
    }

    @GetMapping("/products")
    List<ProductReponseDTO> getAllProducts(){
        List<ProductReponseDTO> products = new ArrayList<>();
        return products;
    }

}
