package com.pawan.productcatalogservice.controllers;

import com.pawan.productcatalogservice.dtos.ProductReponseDTO;
import com.pawan.productcatalogservice.dtos.ProductDTO;
import com.pawan.productcatalogservice.models.Product;
import com.pawan.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    IProductService productService;

    public ProductController(@Qualifier("storageProductService") IProductService productService){
        this.productService= productService;
    }//using @Qualifier annotation

//    public ProductController(IProductService productService){
//        this.productService= productService;
//    }//Using for @Primary annotation

    @PutMapping("/products/{productId}")
    ProductDTO updateProduct(@PathVariable("productId") Long productId,
                             @RequestBody ProductDTO productDTO){
        ProductDTO responseDto = new ProductDTO();
        Product product = productService.replaceProduct(productDTO.
                fromProductDtoToProduct(),productId);

        if(product != null){
            return product.from();
        }
        return null;
    }


    @PostMapping("/products")
    ProductDTO createProduct(@RequestBody ProductDTO productRequestDTO){
        ProductDTO productReponseDTO = new ProductDTO();

        Product product = productService.createProduct(productRequestDTO.
                fromProductDtoToProduct());
        if(product != null){
            return product.from();
        }
        return  productReponseDTO;
    }


    @GetMapping("/products/{id}")
    ResponseEntity<ProductDTO> getProductByProductId(@PathVariable("id") Long id){
        if(id < 0){
            throw  new IllegalArgumentException("Invalid Product ID Zero or Negative");
        }else if(id ==0){
            throw new IllegalArgumentException("Products exist with positive id");
        }
        Product product = productService.getProductById(id);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProductDTO productDTO = product.from();
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }


    @GetMapping("/products")
    List<ProductDTO> getAllProducts(){
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<Product> products = productService.getAllProducts();
        if(products != null){
            for(Product product: products){
                productDTOS.add(product.from());
            }
        }

        return productDTOS;
    }

}
