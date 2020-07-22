package com.community.hululuuuu.domain.wallet;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findTop1ByOrderByWalletDateDesc();
    List<Wallet> findTop5ByOrderByWalletDateDesc();
}
