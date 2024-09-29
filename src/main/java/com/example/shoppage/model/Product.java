package com.example.shoppage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private Double price;
    private Double originalPrice;
    @ManyToMany()
    private Set<Style> style;

    private Double rating;
    private int reviewCount;
    private String promotion;
    private LocalDateTime promotionDate;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImage> productImage;

    private String productDes;

    @ManyToOne
    @JsonManagedReference
    private Size sizes;
    @ManyToOne
    @JsonManagedReference
    private Color color;

    private Boolean favorite;


}
