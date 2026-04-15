package com.pawan.productcatalogservice.repositories;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    /*
    @Test
     */

    @Test
    @Transactional
    public void testJPAMethods() {

        /*
        Get all products in a price range
         */

        String description = productRepository.findDescriptionWhereIdIs(4L);

        assertEquals("electronics", description);
    }

}

