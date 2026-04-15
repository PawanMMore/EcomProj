package com.pawan.productcatalogservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawan.productcatalogservice.dtos.ProductDTO;
import com.pawan.productcatalogservice.models.Product;
import com.pawan.productcatalogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IProductService productService;

    @Test
    public void testGetAllProducts_RunSuccessfully() throws Exception {

        Product product = new Product();
        product.setId(1L);
        product.setProductName("iPhone 14");
        product.setProductDescription("Apple iPhone 14 with A15 Bionic chip");
        product.setProductPrice(999.99);


        List<Product> products = new ArrayList<>();

        products.add(product);

        when(productService.getAllProducts()).thenReturn(products);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("iPhone 14");
        productDTO.setDescription("Apple iPhone 14 with A15 Bionic chip");
        productDTO.setPrice(999.99);

        List<ProductDTO> productDTOS = new ArrayList<>();
        productDTOS.add(productDTO);

        ObjectMapper objectMapper = new ObjectMapper();
        String response = objectMapper.writeValueAsString(productDTOS);


        mockMvc.perform(get("/products")).
                andExpect(status().isOk())
                .andExpect(content().json(response));
    }

}
