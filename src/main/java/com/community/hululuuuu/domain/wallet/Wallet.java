package com.community.hululuuuu.domain.wallet;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Getter
@Table
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int walletId;

    @Column
    private int walletMoney;

    @Column
    private String walletRecord;

    @Column
    private String walletMod;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate walletModdate;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime walletDate;

    @Builder
    public Wallet(int walletMoney, String walletRecord, String walletMod, LocalDate walletModdate, LocalDateTime walletDate) {
        this.walletMoney = walletMoney;
        this.walletRecord = walletRecord;
        this.walletMod = walletMod;
        this.walletModdate = walletModdate;
        this.walletDate = walletDate;
    }

    public void update(int walletMoney, String walletRecord, String walletMod, LocalDate walletModdate) {
        this.walletMoney = walletMoney;
        this.walletRecord = walletRecord;
        this.walletMod = walletMod;
        this.walletModdate = walletModdate;
    }

}
