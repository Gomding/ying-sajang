package com.community.hululuuuu.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
@DynamicUpdate
public class Product implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column
    private String productName; // 제품명

    @Column
    private int productAmount;  // 제품 재고

    @Column
    private int productPrice;   // 제품 가격

    @Column
    private int productCostprice;   // 제품 원가

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime productModdate;   // 수정한 날짜

    @Builder
    public Product(String productName, int productAmount, int productPrice, int productCostprice, LocalDateTime productModdate) {
        this.productName = productName;
        this.productAmount = productAmount;
        this.productPrice = productPrice;
        this.productCostprice = productCostprice;
        this.productModdate = productModdate;
    }

    public void update(ProductCommand productCommand) {
        this.productName = productCommand.getName();
        this.productAmount = productCommand.getAmount();
        this.productPrice = productCommand.getPrice();
        this.productCostprice = productCommand.getCostprice();
        this.productModdate = LocalDateTime.now();
    }

}
