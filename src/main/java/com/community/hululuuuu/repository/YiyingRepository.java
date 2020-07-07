package com.community.hululuuuu.repository;

import com.community.hululuuuu.yiying.Yiying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YiyingRepository extends JpaRepository<Yiying, Long> {
    Yiying findByYiyingName(String yiyingName);
    List<Yiying> findTop5ByOrderByYiyingBuydateDesc();
}
