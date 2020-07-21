package com.community.hululuuuu.yiying;

import com.community.hululuuuu.myComponent.PageableDefault;
import com.community.hululuuuu.repository.YiyingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class YiyingService {

    private YiyingRepository yiyingRepository;

    public YiyingService(YiyingRepository yiyingRepository) {
        this.yiyingRepository = yiyingRepository;
    }

    public Page<Yiying> findBuyList(Pageable pageable) {
        pageable = PageableDefault.setPageable(pageable);
        return yiyingRepository.findAllByOrderByYiyingBuydateDesc(pageable);
    }

    public List<Yiying> findTop5() {
        return yiyingRepository.findTop5ByOrderByYiyingBuydateDesc();
    }

    public void createBuyList(BuyCommand buyCommand) {

        yiyingRepository.save(Yiying.builder()
        .yiyingName(buyCommand.getName())
        .yiyingPrice(buyCommand.getPrice())
        .yiyingAmount(buyCommand.getAmount())
        .yiyingContent(buyCommand.getContent())
        .yiyingBuydate(buyCommand.getBuydate())
        .build());

    }

    public void deleteBuy(Long id) {
        yiyingRepository.deleteById(id);
    }

    public Yiying findById(Long id) {
        return yiyingRepository.getOne(id);
    }

    public void updateBuy(BuyCommand buyCommand, Long id) {
        Yiying yiying = yiyingRepository.getOne(id);
        yiying.update(buyCommand);
        yiyingRepository.save(yiying);
    }

    public int sum1MonthSpendMoney() {

        int sum = 0;

        List<Yiying> oneMonthList =
                yiyingRepository.findByYiyingBuydateBetween(LocalDate.now().minusMonths(1), LocalDate.now().plusDays(1));

        for (int i = 0; i < oneMonthList.size(); i++) {
            sum += oneMonthList.get(i).getYiyingPrice();
        }

        return sum;
    }

    public Page<Yiying> buySearchList(Pageable pageable, LocalDate start, LocalDate end) {
        pageable = PageableDefault.setPageable(pageable);
        return yiyingRepository.findByYiyingBuydateBetween(pageable, start, end);
    }

}
