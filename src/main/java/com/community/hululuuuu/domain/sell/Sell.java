package com.community.hululuuuu.domain.sell;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Table
public class Sell {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellId;            // 판매 아이디

    @Column
    private String sellName;        // 구매자

    @Column
    private String sellProduct;      // 판매 상품

    @Column
    private int sellAmount;         // 판매 수량

    @Column
    private int sellPrice;          // 판매 금액

    @Column
    private String sellMof;         // 결제수단

    @Column
    private int sellProfit;      // 수익

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate sellDate; // 판매 날짜

    @Builder
    public Sell(String sellName, String sellProduct, int sellAmount, int sellPrice, String sellMof, int sellProfit, LocalDate sellDate) {
        this.sellName = sellName;
        this.sellProduct = sellProduct;
        this.sellAmount = sellAmount;
        this.sellPrice = sellPrice;
        this.sellMof = sellMof;
        this.sellProfit = sellProfit;
        this.sellDate = sellDate;
    }

    public void update(String sellName, String sellProduct, int sellAmount, int sellPrice, String sellMof, int sellProfit, LocalDate sellDate) {
        this.sellName = sellName;
        this.sellProduct = sellProduct;
        this.sellAmount = sellAmount;
        this.sellPrice = sellPrice;
        this.sellMof = sellMof;
        this.sellProfit = sellProfit;
        this.sellDate = sellDate;
    }

    public int sum1MonthProfit(List<Sell> list) {
        int sum = 0;
        for (Sell sell : list) {
            sum += sell.getSellProfit();
        }
        return sum;
    }

}
