package com.community.hululuuuu.repository;

import com.community.hululuuuu.sell.Sell;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SellRepository extends JpaRepository<Sell, Long> {
    Page<Sell> findAllByOrderBySellDateDesc(Pageable pageable);
    List<Sell> findTop3ByOrderBySellDateDesc();
    Page<Sell> findBySellDateBetween(Pageable pageable, LocalDate start, LocalDate end);
    List<Sell> findBySellDateBetween(LocalDate start, LocalDate end);

}
