package com.example.shoppage.service;

import com.example.shoppage.model.Product;
import com.example.shoppage.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;


    public Page<Product> findAll(Double minPrice, Double maxPrice, String color, String size, String style, Pageable pageable) {
        return productRepo.findProducts(minPrice, maxPrice, color, size, style,pageable);
    }


    public Product getProduct(Long id) {
        return productRepo.findProductById(id);
    }

    public Page<Product> getProductDesc(Pageable pageable) {
        return productRepo.findAllDesc(pageable);
    }public Page<Product> getProductAsc(Pageable pageable) {
        return productRepo.findAllAsc(pageable);
    }public Page<Product> getProductDescRating(Pageable pageable) {
        return productRepo.findAllDescRating(pageable);
    }

}
