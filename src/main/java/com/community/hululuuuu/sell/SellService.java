package com.community.hululuuuu.sell;

import com.community.hululuuuu.myComponent.PageableDefault;
import com.community.hululuuuu.repository.SellRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Service
public class SellService {

    private SellRepository sellRepository;

    public SellService(SellRepository sellRepository) {
        this.sellRepository = sellRepository;
    }

    // 전체 판매 리스트 + 페이징
    public Page<Sell> findSellList(Pageable pageable) {
        pageable = PageableDefault.setPageable(pageable);
        return sellRepository.findAllByOrderBySellDateDesc(pageable);
    }

    // 판매 생성 메서드
    public void createSell(SellCommand sellCommand) {

        sellRepository.save(Sell.builder()
        .sellName(sellCommand.getName())
        .sellProduct(sellCommand.getProduct())
        .sellAmount(sellCommand.getAmount())
        .sellPrice(sellCommand.getPrice())
        .sellMof(sellCommand.getMof())
        .sellProfit(sellCommand.getProfit())
        .sellDate(sellCommand.getDate())
        .build());

    }

    // 판매내용 최근 3가지 검색 메서드
    public List<Sell> findSellByTop3() {
        return sellRepository.findTop3ByOrderBySellDateDesc();
    }


    // 판매 수정하는 메서드
    public void updateSell(SellCommand sellCommand, Long id) {
        Sell presistSell = sellRepository.getOne(id);
        presistSell.update(sellCommand);
        sellRepository.save(presistSell);
    }

    // 판매 내용 삭제
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
        int sum = 0;
        for (int i = 0; i < sell.size(); i++) {
            sum += sell.get(i).getSellProfit();
        }
        return sum;
    }

    // 판매 검색(날짜 기준)
    public Page<Sell> searchSellList(Pageable pageable, LocalDate start, LocalDate end) {
        pageable = PageableDefault.setPageable(pageable);
        return sellRepository.findBySellDateBetween(pageable, start, end);
    }
}
