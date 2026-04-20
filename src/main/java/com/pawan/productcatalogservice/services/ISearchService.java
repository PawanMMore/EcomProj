package com.pawan.productcatalogservice.services;

import com.pawan.productcatalogservice.dtos.SortParam;
import com.pawan.productcatalogservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearchService {
    Page<Product> searchProducts(
            String query,
            Integer pageNumber,
            Integer pageSize,
            List<SortParam> sortParams
    );
}
