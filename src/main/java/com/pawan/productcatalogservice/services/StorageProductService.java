package com.pawan.productcatalogservice.services;

import com.pawan.productcatalogservice.models.Product;
import com.pawan.productcatalogservice.models.State;
import com.pawan.productcatalogservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("storageProductService")
@Primary
public class StorageProductService implements IProductService {

    private ProductRepository productRepository;

    public StorageProductService(ProductRepository productRepository){
        this.productRepository =productRepository;
    }

    @Override
    public Product getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
//        return optionalProduct.orElse(null);
        if(optionalProduct.isEmpty()){
            return null;
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product inputProduct) {
        Optional<Product> optionalProduct = productRepository.findById(inputProduct.getId());

        if(optionalProduct.isEmpty()){
            return productRepository.save(inputProduct);
        }else {
            return null;
        }

    }

    @Override
    public Product replaceProduct(Product prod, Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            return null;
        }
        else {
            prod.setId(productId);
            prod.setCreatedAt(optionalProduct.get().getCreatedAt());
            return productRepository.save(prod);
        }
    }

    public boolean deleteProduct(Long productId){
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(optionalProduct.isEmpty()) {
            return false;
        }else {
            Product product = optionalProduct.get();
            if(product.getState().equals(State.ACTIVE)){
                product.setState(State.INACTIVE);
                productRepository.save(product);
                return true;
            }
            return false;
        }
    }
}
