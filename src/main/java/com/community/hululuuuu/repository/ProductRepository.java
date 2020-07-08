package com.community.hululuuuu.repository;

import com.community.hululuuuu.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductName(String productName);
    Page<Product> findAllByOrderByProductModdateDesc(Pageable pageable);
}
