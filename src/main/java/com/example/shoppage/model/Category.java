package com.example.shoppage.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String CategoriesName;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
