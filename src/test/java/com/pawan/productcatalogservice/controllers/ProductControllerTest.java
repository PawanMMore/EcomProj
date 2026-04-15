package com.pawan.productcatalogservice.controllers;

import com.pawan.productcatalogservice.dtos.ProductDTO;
import com.pawan.productcatalogservice.models.Product;
import com.pawan.productcatalogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    ProductController productController;

    @MockitoBean
    IProductService productService;

    @Test
    public void testGetProductById_WithValidId_RunSuccessfully(){

        /*
        Arrange
         */
        Product product = new Product();
        product.setId(1L);
        product.setProductName("iPhone 14");
        product.setProductDescription("Apple iPhone 14 with A15 Bionic chip");
        product.setProductPrice(999.99);

        when(productService.getProductById(1L)).thenReturn(product);

        /*
        Act
         */

        ResponseEntity<ProductDTO> productDTOResponseEntity =
                productController.getProductByProductId(1L);

        /*
        Assert
         */
        assertNotNull(productDTOResponseEntity);
        assertNotNull(productDTOResponseEntity.getBody());
        assertEquals(product.getId(),
                productDTOResponseEntity.getBody().getId());
        assertEquals(product.getProductName(),
                productDTOResponseEntity.getBody().getName());
        assertEquals(product.getProductDescription(),
                productDTOResponseEntity.getBody().getDescription());

        verify(productService, times(1)).
                getProductById(1L);

    }

    @Test
    public void testGetProductById_WithNegativeId_ThrowsIllegalArgumentException(){

        assertThrows(IllegalArgumentException.class, () -> {
            productController.getProductByProductId(-1L);
        });

        verify(productService, times(0)).
                getProductById(-1L);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productController.getProductByProductId(-1L);
        });

        assertEquals("Product Id not found", exception.getMessage());
    }
}