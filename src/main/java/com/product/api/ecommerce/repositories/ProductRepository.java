package com.product.api.ecommerce.repositories;

import com.product.api.ecommerce.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    @EntityGraph(attributePaths = "category")
    Page<Product> findByCategoryId(Byte categoryId, Pageable pageable);

    @EntityGraph(attributePaths = "category")
    @Query("SELECT p FROM Product p")
    Page<Product> findAllWithCategory(Pageable pageable);
}
