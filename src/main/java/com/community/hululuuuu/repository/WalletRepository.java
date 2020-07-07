package com.community.hululuuuu.repository;

import com.community.hululuuuu.wallet.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findTop1ByOrderByWalletDateDesc();
    List<Wallet> findTop5ByOrderByWalletDateDesc();
}
