package com.pawan.productcatalogservice.controllers;

import com.pawan.productcatalogservice.dtos.SearchRequestDto;
import com.pawan.productcatalogservice.models.Product;
import com.pawan.productcatalogservice.services.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @PostMapping
    public Page<Product> search(@RequestBody SearchRequestDto searchRequestDto) {
        Page<Product> products = searchService.searchProducts(
                searchRequestDto.getQuery(),
                searchRequestDto.getPageNumber(),
                searchRequestDto.getPageSize(),
                searchRequestDto.getSortParams()
        );
        return products;
    }
}
