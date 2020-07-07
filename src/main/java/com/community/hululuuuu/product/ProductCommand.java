package com.community.hululuuuu.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductCommand {

    private String name; // 제품명

    private int amount;  // 제품 재고

    private int price;   // 제품 가격

    private int costprice;   // 제품 원가
}
