package com.pawan.productcatalogservice.repositories;

import com.pawan.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long productId);

    @Override
    List<Product> findAll();

    @Override
    Product save(Product product);

    List<Product> findByProductPriceBetween(Double low, Double high);

    @Query("SELECT p.productDescription FROM Product p WHERE p.id = :id") //HQL
    String findDescriptionWhereIdIs(@Param("id") Long id);

}
