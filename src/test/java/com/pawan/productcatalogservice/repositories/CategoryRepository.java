package com.pawan.productcatalogservice.repositories;

import com.pawan.productcatalogservice.models.Category;
import com.pawan.productcatalogservice.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

//    @Test
//    @Transactional
//    public void testFetchTypes() {
////        1. LAZY
////        2. EAGER
//
//        Optional<Category> categoryOpt = categoryRepository.findById(1L);
//
//        if(categoryOpt.isPresent()){
//            Category category = categoryOpt.get();
//
//            System.out.println();
//
//            for(Product product: category.getProducts()){
//                System.out.println(product.getProductName());
//            }
//            //}
//        }
//
//    }

    @Test
    @Transactional
    void nPlusOne(){
        List<Category> categories = categoryRepository.findAll();
        for(Category category: categories){
            // for each category i want to get all products

            for(Product product: category.getProducts()){
                System.out.println(product.getProductName());
            }
        }
    }

}
