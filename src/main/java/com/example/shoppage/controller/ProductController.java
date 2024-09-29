package com.example.shoppage.controller;

import com.example.shoppage.model.Product;
import com.example.shoppage.model.dto.ProductDto;
import com.example.shoppage.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/products")

public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public ResponseEntity <Page<ProductDto>> getProducts(@RequestParam(required = false) Double minPrice,
                                                         @RequestParam(required = false) Double maxPrice,
                                                         @RequestParam(required = false) String color,
                                                          @RequestParam(required = false) String size,
                                                          @RequestParam(required = false) String style,
                                                          @RequestParam(defaultValue = "0")int page,
                                                          @RequestParam(defaultValue = "3")int number) {
        Pageable pageable  = PageRequest.of(page, number);
        Page<Product> products = productService.findAll(minPrice,maxPrice, color, size, style, pageable);
        Page<ProductDto> dtoPage = products.map(product -> new ProductDto(product.getProductName(), product.getRating(),product.getPrice(),product.getOriginalPrice()));
        if(products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dtoPage, HttpStatus.OK) ;
    }
    @GetMapping("/find/{id}")
    public ResponseEntity <Product> getProductById(@PathVariable Long id){
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @GetMapping("/list/price/desc")
    public ResponseEntity<Page<ProductDto>> getProductPriceDesc(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "3") int number) {
        Pageable pageable = PageRequest.of(page, number);
        Page<Product> products = productService.getProductDesc(pageable);
        Page<ProductDto>productDtos = products.map(product -> new ProductDto(product.getProductName(), product.getRating(),product.getPrice(),product.getOriginalPrice()));
        if(products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK) ;
    }

    @GetMapping("/list/price/asc")
    public ResponseEntity<Page<ProductDto>> getProductPriceAsc(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "3") int number) {
        Pageable pageable = PageRequest.of(page, number);
        Page<Product> products = productService.getProductAsc(pageable);
        Page<ProductDto>productDtos = products.map(product -> new ProductDto(product.getProductName(), product.getRating(),product.getPrice(),product.getOriginalPrice()));
        if(products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK) ;
    }

    @GetMapping("/list/rating/desc")
    public ResponseEntity<Page<ProductDto>> getProductRatingDesc(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "3") int number) {
        Pageable pageable = PageRequest.of(page, number);
        Page<Product> products = productService.getProductDescRating(pageable);
        Page<ProductDto>productDtos = products.map(product -> new ProductDto(product.getProductName(), product.getRating(),product.getPrice(),product.getOriginalPrice()));
        if(products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK) ;
    }

}
