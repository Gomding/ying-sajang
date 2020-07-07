package com.community.hululuuuu.wallet;

import com.community.hululuuuu.repository.SellRepository;
import com.community.hululuuuu.repository.WalletRepository;
import com.community.hululuuuu.repository.YiyingRepository;
import com.community.hululuuuu.sell.Sell;
import com.community.hululuuuu.sell.SellCommand;
import com.community.hululuuuu.yiying.BuyCommand;
import com.community.hululuuuu.yiying.Yiying;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WalletService {

    private WalletRepository walletRepository;
    private YiyingRepository yiyingRepository;
    private SellRepository sellRepository;

    public WalletService(WalletRepository walletRepository, YiyingRepository yiyingRepository, SellRepository sellRepository) {
        this.walletRepository = walletRepository;
        this.yiyingRepository = yiyingRepository;
        this.sellRepository = sellRepository;
    }

    public List<Wallet> findTop5FromWallet() {
        return walletRepository.findTop5ByOrderByWalletDateDesc();
    }

    public Wallet nowWallet() {
        return walletRepository.findTop1ByOrderByWalletDateDesc();
    }

    public void plusWalletMoney(SellCommand sellCommand) {
        int nowWalletMoney = walletRepository.findTop1ByOrderByWalletDateDesc().getWalletMoney();
        int sellMoney = sellCommand.getProfit();
        walletRepository.save(
                Wallet.builder()
                .walletMoney(nowWalletMoney + sellMoney)
                .walletRecord(sellCommand.getProduct())
                .walletMod("+ " + sellMoney + "원")
                .walletModdate(sellCommand.getDate())
                .walletDate(LocalDateTime.now())
                .build()
        );
    }

    public void updatePlusWalletMoney(SellCommand sellCommand, Long id) {
        int nowWalletMoney = walletRepository.findTop1ByOrderByWalletDateDesc().getWalletMoney();
        int oldSellMoney = sellRepository.getOne(id).getSellProfit();
        int newSellMoney = sellCommand.getProfit();
        int diffMoney = oldSellMoney - newSellMoney;
        String sign = "+";

        if (diffMoney < 0) sign = "-";

            walletRepository.save(
                    Wallet.builder()
                    .walletMoney(nowWalletMoney + diffMoney)
                    .walletRecord(sellCommand.getProduct())
                    .walletMod(sign + Math.abs(diffMoney) + " (" + oldSellMoney + " - > " + newSellMoney + " 수정됨)")
                    .walletModdate(sellCommand.getDate())
                    .walletDate(LocalDateTime.now())
                    .build()
            );
    }

    public void canclePlusWalletMoney(Long id) {

        Sell sell = sellRepository.getOne(id);
        int nowWalletMoney = walletRepository.findTop1ByOrderByWalletDateDesc().getWalletMoney();
        int sellMoney = sell.getSellProfit();
        walletRepository.save(
                Wallet.builder()
                        .walletMoney(nowWalletMoney - sellMoney)
                        .walletRecord(sell.getSellProduct() + "취소")
                        .walletMod("- " + sellMoney + "원")
                        .walletModdate(sell.getSellDate())
                        .walletDate(LocalDateTime.now())
                        .build()
        );
    }

    public void minusWalletMoney(BuyCommand buyCommand) {
        int nowWalletMoney = walletRepository.findTop1ByOrderByWalletDateDesc().getWalletMoney();
        int buyMoney = buyCommand.getPrice();
        walletRepository.save(
                Wallet.builder()
                .walletMoney(nowWalletMoney - buyMoney)
                .walletRecord(buyCommand.getName())
                .walletMod("- " + buyMoney + "원")
                .walletModdate(buyCommand.getBuydate())
                .walletDate(LocalDateTime.now())
                .build()
        );
    }

    public void cancleMinusWalletMoney(Long id) {

        Yiying yiying = yiyingRepository.getOne(id);
        int nowWalletMoney = walletRepository.findTop1ByOrderByWalletDateDesc().getWalletMoney();
        int buyMoney = yiying.getYiyingPrice();
        walletRepository.save(
                Wallet.builder()
                        .walletMoney(nowWalletMoney + buyMoney)
                        .walletRecord(yiying.getYiyingName() + "취소")
                        .walletMod("+ " + buyMoney + "원")
                        .walletModdate(yiying.getYiyingBuydate())
                        .walletDate(LocalDateTime.now())
                        .build()
        );
    }

    public void updateMinusWalletMoney(BuyCommand buyCommand, Long id) {
        int nowWalletMoney = walletRepository.findTop1ByOrderByWalletDateDesc().getWalletMoney();
        int oldBuyMoney = yiyingRepository.getOne(id).getYiyingPrice();
        int newBuyMoney = buyCommand.getPrice();
        int diffMoney = oldBuyMoney - newBuyMoney;
        String sign = "+";

        if (diffMoney < 0) sign = "-";

        walletRepository.save(
                Wallet.builder()
                        .walletMoney(nowWalletMoney + diffMoney)
                        .walletRecord(buyCommand.getName())
                        .walletMod(sign + Math.abs(diffMoney) + " (" + oldBuyMoney + " - > " + newBuyMoney + " 수정됨)")
                        .walletModdate(buyCommand.getBuydate())
                        .walletDate(LocalDateTime.now())
                        .build()
        );
    }


}
