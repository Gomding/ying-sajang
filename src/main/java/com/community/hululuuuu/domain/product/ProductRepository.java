package com.community.hululuuuu.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductName(String productName);
    Page<Product> findAllByOrderByProductModdateDesc(Pageable pageable);
    Page<Product> findByProductName(Pageable pageable, String product);
}
