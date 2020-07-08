package com.community.hululuuuu.sell;

import com.community.hululuuuu.repository.SellRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<Sell> findSellList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());
        return sellRepository.findAllByOrderBySellDateDesc(pageable);
    }


    public void createSell(@Valid SellCommand sellCommand) {

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

    public List<Sell> findSellByyTop3() {
        return sellRepository.findTop3ByOrderBySellDateDesc();
    }

    public Sell findSellById(Long id) {
        return sellRepository.findById(id).orElse(new Sell());
    }

    public void updateSell(SellCommand sellCommand, Long id) {
        Sell presistSell = sellRepository.getOne(id);
        presistSell.update(sellCommand);
        sellRepository.save(presistSell);
    }

    public void deleteSell(Long id) {
        sellRepository.deleteById(id);
    }

    public Sell sellFindById(Long id) {
        return sellRepository.getOne(id);
    }

    public int select1MonthPrice() {
        List<Sell> sell = sellRepository.findBySellDateBetween(LocalDate.now().minusMonths(1), LocalDate.now().plusDays(1));
        int sum = 0;
        for (int i = 0; i < sell.size(); i++) {
            sum += sell.get(i).getSellProfit();
        }
        return sum;
    }
}
