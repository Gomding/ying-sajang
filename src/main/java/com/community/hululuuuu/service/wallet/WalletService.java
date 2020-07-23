package com.community.hululuuuu.service.wallet;

import com.community.hululuuuu.domain.sell.Sell;
import com.community.hululuuuu.domain.sell.SellRepository;
import com.community.hululuuuu.domain.wallet.Wallet;
import com.community.hululuuuu.domain.wallet.WalletRepository;
import com.community.hululuuuu.domain.yiying.Yiying;
import com.community.hululuuuu.domain.yiying.YiyingRepository;
import com.community.hululuuuu.web.dto.BuySaveRequestDto;
import com.community.hululuuuu.web.dto.SellRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final YiyingRepository yiyingRepository;
    private final SellRepository sellRepository;

    public List<Wallet> findTop5FromWallet() {
        return walletRepository.findTop5ByOrderByWalletDateDesc();
    }

    public Wallet nowWallet() {
        return walletRepository.findTop1ByOrderByWalletDateDesc();
    }

    @Transactional
    public void plusWalletMoney(SellRequestDto requestDto) {
        int nowWalletMoney = walletRepository.findTop1ByOrderByWalletDateDesc().getWalletMoney();
        int sellMoney = requestDto.getProfit();
        walletRepository.save(
                Wallet.builder()
                .walletMoney(nowWalletMoney + sellMoney)
                .walletRecord(requestDto.getProduct())
                .walletMod("+ " + sellMoney + "원")
                .walletModdate(requestDto.getDate())
                .walletDate(LocalDateTime.now())
                .build()
        );
    }

    @Transactional
    public void updatePlusWalletMoney(SellRequestDto requestDto, Long id) {
        int nowWalletMoney = walletRepository.findTop1ByOrderByWalletDateDesc().getWalletMoney();
        int oldSellMoney = sellRepository.getOne(id).getSellProfit();
        int newSellMoney = requestDto.getProfit();
        int diffMoney = oldSellMoney - newSellMoney;
        String sign = diffMoney >= 0 ? "+" : "-";

        if (diffMoney != 0) {   // 돈이 변하지 않으면 계좌 내역을 생성할 필요가 없음

            walletRepository.save(
                    Wallet.builder()
                            .walletMoney(nowWalletMoney + diffMoney)
                            .walletRecord(requestDto.getProduct())
                            .walletMod(sign + Math.abs(diffMoney) + " (" + oldSellMoney + " - > " + newSellMoney + " 수정됨)")
                            .walletModdate(requestDto.getDate())
                            .walletDate(LocalDateTime.now())
                            .build()
            );
        }
    }

    public void cancelPlusWalletMoney(Long id) {

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

    @Transactional
    public void minusWalletMoney(BuySaveRequestDto requestDto) {
        int nowWalletMoney = walletRepository.findTop1ByOrderByWalletDateDesc().getWalletMoney();
        int buyMoney = requestDto.getPrice();
        walletRepository.save(
                Wallet.builder()
                .walletMoney(nowWalletMoney - buyMoney)
                .walletRecord(requestDto.getName())
                .walletMod("- " + buyMoney + "원")
                .walletModdate(requestDto.getBuydate())
                .walletDate(LocalDateTime.now())
                .build()
        );
    }

    @Transactional
    public void cancelMinusWalletMoney(Long id) {

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

    @Transactional
    public void updateMinusWalletMoney(BuySaveRequestDto requestDto, Long id) {
        int nowWalletMoney = walletRepository.findTop1ByOrderByWalletDateDesc().getWalletMoney();
        int oldBuyMoney = yiyingRepository.getOne(id).getYiyingPrice();
        int newBuyMoney = requestDto.getPrice();
        int diffMoney = oldBuyMoney - newBuyMoney;
        String sign = diffMoney >= 0 ? "+ " : "- ";

        if (diffMoney != 0) {

            walletRepository.save(
                    Wallet.builder()
                            .walletMoney(nowWalletMoney + diffMoney)
                            .walletRecord(requestDto.getName())
                            .walletMod(sign + Math.abs(diffMoney) + " (" + oldBuyMoney + " - > " + newBuyMoney + " 수정됨)")
                            .walletModdate(requestDto.getBuydate())
                            .walletDate(LocalDateTime.now())
                            .build()
            );
        }
    }


}
