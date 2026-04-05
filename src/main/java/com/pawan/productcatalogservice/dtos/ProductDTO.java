package com.pawan.productcatalogservice.dtos;

import com.pawan.productcatalogservice.models.Category;
import com.pawan.productcatalogservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private CategoryDTO category;
    private Double price;
    private String imageUrl;

    public Product fromProductDtoToProduct(){
        Product product = new Product();
        product.setId(this.getId());
        product.setProductName(this.getName());
        product.setProductDescription(this.getDescription());
        product.setImageURL(this.getImageUrl());
        product.setProductPrice(this.getPrice());
        if(product.getCategory() != null){
            Category category1 = new Category();
            category1.setName(this.getCategory().getName());
            category1.setId(this.getCategory().getId());
            category1.setDescription(this.getCategory().getDescription());
            product.setCategory(category1);
        }
        return product;
    }
}
