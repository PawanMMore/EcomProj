package com.pawan.productcatalogservice.dtos;

import com.pawan.productcatalogservice.models.Category;
import com.pawan.productcatalogservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakestoreProductDto {
    private Long id;
    private String title;
    private String description;
    private String image;
    private Double price;
    private String category;

    public Product from(FakestoreProductDto fakestoreProductDto){
        Product product= new Product();
        product.setId(fakestoreProductDto.getId());
        product.setProductName(fakestoreProductDto.getTitle());
        product.setProductDescription(fakestoreProductDto.getDescription());
        product.setProductPrice(fakestoreProductDto.getPrice());
        product.setProductPrice(fakestoreProductDto.getPrice());
        product.setImageURL(fakestoreProductDto.getImage());

        Category category1 = new Category();
        category1.setName(fakestoreProductDto.getCategory());
        product.setCategory(category1);
        return product;
    }
}
