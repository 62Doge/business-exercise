package com.xa.business_exercise.service;

import com.xa.business_exercise.model.Category;
import com.xa.business_exercise.model.Product;
import com.xa.business_exercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public List<Product> findByActiveTrue(){
        return productRepository.findByActiveTrue();
    }

    public List<Product> findByCategoryId(Long id) {
        return productRepository.findByCategoryId(id);
    }

    public List<Product> findByCategoryActiveTrue(){
        return productRepository.findByCategoryActiveTrue();
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }
}
