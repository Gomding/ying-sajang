package com.community.hululuuuu.sell;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class SellCommand {

    private String name;        // 구매자

    private String product;      // 판매 상품

    private int amount;         // 판매 수량

    private int price;          // 판매 금액

    private String mof;         // 결제수단

    private int profit;         // 수익

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date; // 판매 날짜

}
