package com.community.hululuuuu.repository;

import com.community.hululuuuu.yiying.Yiying;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface YiyingRepository extends JpaRepository<Yiying, Long> {
    Yiying findByYiyingName(String yiyingName);
    List<Yiying> findTop5ByOrderByYiyingBuydateDesc();
    Page<Yiying> findAllByOrderByYiyingBuydateDesc(Pageable pageable);
    List<Yiying> findByYiyingBuydateBetween(LocalDate start, LocalDate end);
}
