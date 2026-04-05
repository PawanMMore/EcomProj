package com.pawan.productcatalogservice.models;

import com.pawan.productcatalogservice.dtos.CategoryDTO;
import com.pawan.productcatalogservice.dtos.FakestoreProductDto;
import com.pawan.productcatalogservice.dtos.ProductDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private  String productName;
    private String productDescription;
    private Double productPrice;
    private String imageURL;
    private Category category;

    //conver product to fakstoreProductDto
    public FakestoreProductDto toFakestoreProductDto(){
        FakestoreProductDto fakestoreProductDto = new FakestoreProductDto();
        fakestoreProductDto.setId(this.getId());
        fakestoreProductDto.setTitle(this.getProductName());
        fakestoreProductDto.setPrice(this.getProductPrice());
        fakestoreProductDto.setDescription(this.getProductDescription());
        fakestoreProductDto.setImage(this.getImageURL());
        if(this.getCategory() != null){
            fakestoreProductDto.setCategory(this.getCategory().getName());
        }
        return fakestoreProductDto;
    }

    // convert product to ProductDto
    public ProductDTO from(){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(this.getId());
        productDTO.setName(this.getProductName());
        productDTO.setDescription(this.getProductDescription());
        productDTO.setPrice(this.getProductPrice());
        productDTO.setImageUrl(this.getImageURL());
        if(this.getCategory() != null){
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(this.getCategory().getId());
            categoryDTO.setDescription(this.getCategory().getDescription());
            categoryDTO.setName(this.getCategory().getName());
            productDTO.setCategory(categoryDTO);
        }
        return productDTO;

    }
}
