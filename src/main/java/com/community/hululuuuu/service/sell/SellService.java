package com.community.hululuuuu.service.sell;

import com.community.hululuuuu.domain.sell.Sell;
import com.community.hululuuuu.domain.sell.SellRepository;
import com.community.hululuuuu.myComponent.PageableDefault;
import com.community.hululuuuu.web.dto.SellRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SellService {

    private final SellRepository sellRepository;

    // 전체 판매 리스트 + 페이징
    public Page<Sell> findSellList(Pageable pageable) {
        pageable = PageableDefault.setPageable(pageable);
        return sellRepository.findAllByOrderBySellDateDesc(pageable);
    }

    // 판매 생성 메서드
    @Transactional
    public Long createSell(SellRequestDto requestDto) {

        return sellRepository.save(requestDto.toEntity()).getSellId();

    }

    // 판매내용 최근 3가지 검색 메서드
    public List<Sell> findSellByTop3() {
        return sellRepository.findTop3ByOrderBySellDateDesc();
    }


    // 판매 수정하는 메서드
    @Transactional
    public void updateSell(SellRequestDto requestDto, Long id) {
        Sell persistSell = sellRepository.getOne(id);
        persistSell.update(requestDto.getName(),
                requestDto.getProduct(),
                requestDto.getAmount(),
                requestDto.getPrice(),
                requestDto.getMof(),
                requestDto.getProfit(),
                requestDto.getDate());
    }

    // 판매 내용 삭제
    @Transactional
    public void deleteSell(Long id) {
        sellRepository.deleteById(id);
    }

    // id 로 판매의 상세정보 가져오기 - > 수정폼에 사용
    public Sell sellFindById(Long id) {
        return sellRepository.getOne(id);
    }

    // 최근 1달 판매 검색
    public int sum1MonthProfit() {
        List<Sell> sell = sellRepository.findBySellDateBetween(LocalDate.now().minusMonths(1), LocalDate.now().plusDays(1));
        return new Sell().sum1MonthProfit(sell);
    }

    // 판매 검색(날짜 기준)
    public Page<Sell> searchSellList(Pageable pageable, LocalDate start, LocalDate end) {
        pageable = PageableDefault.setPageable(pageable);
        return sellRepository.findBySellDateBetween(pageable, start, end);
    }
}
