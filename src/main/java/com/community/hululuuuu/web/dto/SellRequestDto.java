package com.community.hululuuuu.web.dto;

import com.community.hululuuuu.domain.sell.Sell;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class SellRequestDto {

    private String name;        // 구매자
    private String product;      // 판매 상품
    private int amount;         // 판매 수량
    private int price;          // 판매 금액
    private String mof;         // 결제수단
    private int profit;         // 수익

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date; // 판매 날짜

    @Builder
    public SellRequestDto(String name, String product, int amount, int price, String mof, int profit) {
        this.name = name;
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.mof = mof;
        this.profit = profit;
    }

    public Sell toEntity() {
        return Sell.builder()
                .sellName(name)
                .sellProduct(product)
                .sellAmount(amount)
                .sellPrice(price)
                .sellMof(mof)
                .sellProfit(profit)
                .sellDate(date)
                .build();
    }

}
