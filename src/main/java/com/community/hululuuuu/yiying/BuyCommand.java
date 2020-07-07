package com.community.hululuuuu.yiying;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
public class BuyCommand {

    private String name;  // 구매한 상품의 이름

    private int price;   // 구매한 상품의 가격

    private int amount;   // 구매한 물품 수량

    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate buydate;
}
