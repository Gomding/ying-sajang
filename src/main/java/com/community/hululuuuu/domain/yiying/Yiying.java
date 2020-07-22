package com.community.hululuuuu.domain.yiying;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Entity
@Table
@Getter
public class Yiying {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long yiyingId;      // 구매 고유 아이디

    @Column
    private String yiyingName;  // 구매한 상품의 이름

    @Column
    private int yiyingPrice;   // 구매한 상품의 가격

    @Column
    private int yiyingAmount;   // 구매한 물품 수량

    @Column
    private String yiyingContent;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate yiyingBuydate;    // 구매한 날짜

    @Builder
    public Yiying(String yiyingName, int yiyingPrice, int yiyingAmount, String yiyingContent, LocalDate yiyingBuydate) {
        this.yiyingName = yiyingName;
        this.yiyingPrice = yiyingPrice;
        this.yiyingAmount = yiyingAmount;
        this.yiyingContent = yiyingContent;
        this.yiyingBuydate = yiyingBuydate;
    }

    public void update(String yiyingName, int yiyingPrice, int yiyingAmount, String yiyingContent, LocalDate yiyingBuydate) {
        this.yiyingName = yiyingName;
        this.yiyingPrice = yiyingPrice;
        this.yiyingAmount = yiyingAmount;
        this.yiyingContent = yiyingContent;
        this.yiyingBuydate = yiyingBuydate;
    }

    public int sum1MonthSpendMoney(List<Yiying> oneMonthList) {

        int sum = 0;
        for (Yiying yiying : oneMonthList) {
            sum += yiying.yiyingPrice;
        }

        return sum;
    }

}
