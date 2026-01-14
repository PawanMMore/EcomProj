package com.pawan.productcatalogservice.models;

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
}
