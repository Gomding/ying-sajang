package com.community.hululuuuu.service.yiying;

import com.community.hululuuuu.domain.yiying.Yiying;
import com.community.hululuuuu.domain.yiying.YiyingRepository;
import com.community.hululuuuu.myComponent.PageableDefault;
import com.community.hululuuuu.web.dto.BuySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class YiyingService {

    private final YiyingRepository yiyingRepository;

    public Page<Yiying> findBuyList(Pageable pageable) {
        pageable = PageableDefault.setPageable(pageable);
        return yiyingRepository.findAllByOrderByYiyingBuydateDesc(pageable);
    }

    public List<Yiying> findTop5() {
        return yiyingRepository.findTop5ByOrderByYiyingBuydateDesc();
    }

    @Transactional
    public Long createBuyList(BuySaveRequestDto requestDto) {

        return yiyingRepository.save(requestDto.toEntity()).getYiyingId();

    }

    @Transactional
    public void deleteBuy(Long id) {
        yiyingRepository.deleteById(id);
    }

    public Yiying findById(Long id) {
        return yiyingRepository.getOne(id);
    }

    @Transactional
    public void updateBuy(BuySaveRequestDto requestDto, Long id) {
        Yiying yiying = yiyingRepository.getOne(id);
        yiying.update(requestDto.getName(),
                requestDto.getPrice(),
                requestDto.getAmount(),
                requestDto.getContent(),
                requestDto.getBuydate());
    }

    public int sum1MonthSpendMoney() {
        List<Yiying> oneMonthList =
                yiyingRepository.findByYiyingBuydateBetween(LocalDate.now().minusMonths(1), LocalDate.now().plusDays(1));
        return new Yiying().sum1MonthSpendMoney(oneMonthList);
    }

    public Page<Yiying> buySearchList(Pageable pageable, LocalDate start, LocalDate end) {
        pageable = PageableDefault.setPageable(pageable);
        return yiyingRepository.findByYiyingBuydateBetween(pageable, start, end);
    }

}
