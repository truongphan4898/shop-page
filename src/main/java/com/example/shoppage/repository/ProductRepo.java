package com.example.shoppage.repository;

import com.example.shoppage.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
    public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p " +
                    "JOIN p.color c " +
                    "JOIN p.sizes s " +
                    "JOIN p.style st " +
                    "WHERE (:minPrice IS NULL OR p.price >= :minPrice) " +
                    "AND (:maxPrice IS NULL OR p.price <= :maxPrice) " +
                    "AND (:color IS NULL OR c.colorName = :color) " +
                    "AND (:size IS NULL OR s.productSize = :size) " +
                    "AND (:style IS NULL OR st.styleName = :style)")
    Page<Product> findProducts(@Param("minPrice") Double minPrice,
                                       @Param("maxPrice") Double maxPrice,
                                       @Param("color") String color,
                                       @Param("size") String size,
                                       @Param("style") String style,
                                       Pageable pageable);

    Page<Product>findAll( Pageable pageable);




    Product findProductById(Long id);


    @Query(" SELECT p from Product p ORDER BY p.price DESC ")
    Page<Product> findAllDesc(Pageable pageable);

    @Query(" SELECT p from Product p ORDER BY p.price ASC ")
    Page<Product> findAllAsc(Pageable pageable);

    @Query(" SELECT p from Product p ORDER BY p.rating DESC ")
    Page<Product> findAllDescRating(Pageable pageable);


}
