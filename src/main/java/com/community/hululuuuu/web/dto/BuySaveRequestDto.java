package com.community.hululuuuu.web.dto;

import com.community.hululuuuu.domain.yiying.Yiying;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
public class BuySaveRequestDto {

    private String name;  // 구매한 상품의 이름

    private int price;   // 구매한 상품의 가격

    private int amount;   // 구매한 물품 수량

    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate buydate;

    @Builder
    public BuySaveRequestDto(String name, int price, int amount, String content, LocalDate buydate) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.content = content;
        this.buydate = buydate;
    }

    public Yiying toEntity() {
        return Yiying.builder()
                .yiyingName(name)
                .yiyingPrice(price)
                .yiyingAmount(amount)
                .yiyingContent(content)
                .yiyingBuydate(buydate)
                .build();
    }

}
