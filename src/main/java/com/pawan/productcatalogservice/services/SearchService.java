package com.pawan.productcatalogservice.services;

import com.pawan.productcatalogservice.dtos.SortParam;
import com.pawan.productcatalogservice.dtos.SortType;
import com.pawan.productcatalogservice.models.Product;
import com.pawan.productcatalogservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService implements ISearchService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> searchProducts(String query, Integer pageNumber, Integer pageSize, List<SortParam> sortParams) {
        Sort sort=null;
        if(!sortParams.isEmpty()) {
            if(sortParams.get(0).getOrder().equals("ASC")){
                sort = sort.by(sortParams.get(0).getParamName()).ascending();
            }else{
                sort = sort.by(sortParams.get(0).getParamName()).descending();
            }
            for(int i=1;i<sortParams.size();i++){
                if(sortParams.get(i).getOrder().equals("ASC")) {
                    sort = sort.and(sort.by(sortParams.get(i).getParamName())).ascending();
                }else{
                    sort = sort.and(sort.by(sortParams.get(i).getParamName())).descending();
                }
            }
        }

        return productRepository.findByProductName(
                query,
                PageRequest.of(pageNumber, pageSize, sort)
        );
    }
}
