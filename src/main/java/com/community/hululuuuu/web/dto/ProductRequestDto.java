package com.community.hululuuuu.web.dto;

import com.community.hululuuuu.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class ProductRequestDto {

    private String name; // 제품명
    private int amount;  // 제품 재고
    private int price;   // 제품 가격
    private int costprice;   // 제품 원가
    private LocalDateTime moddate;

    @Builder
    public ProductRequestDto(String name, int amount, int price, int costprice, LocalDateTime moddate) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.costprice = costprice;
        this.moddate = moddate;
    }

    public Product toEntity() {
        return Product.builder()
                .productName(name)
                .productAmount(amount)
                .productPrice(price)
                .productCostprice(costprice)
                .productModdate(LocalDateTime.now())
                .build();
    }

}
