package com.pawan.productcatalogservice.controllers;

import com.pawan.productcatalogservice.dtos.ProductReponseDTO;
import com.pawan.productcatalogservice.dtos.ProductDTO;
import com.pawan.productcatalogservice.models.Product;
import com.pawan.productcatalogservice.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    IProductService productService;

    public ProductController(IProductService productService){
        this.productService= productService;
    }

    @PostMapping("/newProduct")
    ProductReponseDTO createProduct(@RequestBody ProductDTO productRequestDTO){
        ProductReponseDTO productReponseDTO = new ProductReponseDTO();
        return  productReponseDTO;
    }

    @GetMapping("/products/{id}")
    ResponseEntity<ProductDTO> getProductByProductId(@PathVariable("id") Long id){
        if(id < 1){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Product product = productService.getProductById(id);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProductDTO productDTO = product.from();
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

//    @GetMapping("/products")
//    List<ProductReponseDTO> getAllProducts(){
//        List<ProductReponseDTO> products = new ArrayList<>();
//        return products;
//    }

}
