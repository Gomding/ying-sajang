package com.community.hululuuuu.repository;

import com.community.hululuuuu.sell.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellRepository extends JpaRepository<Sell, Long> {
    Sell findBySellName(String sellName);
    List<Sell> findTop3ByOrderBySellDateDesc();
}
