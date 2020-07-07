package com.community.hululuuuu.restController;

import com.community.hululuuuu.sell.SellCommand;
import com.community.hululuuuu.sell.SellService;
import com.community.hululuuuu.wallet.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sell")
public class SellRestController {

    private SellService sellService;
    private WalletService walletService;

    public SellRestController(SellService sellService, WalletService walletService) {
        this.sellService = sellService;
        this.walletService = walletService;
    }

    @PutMapping("/sellMod/{id}")
    public ResponseEntity<?> updateSell(@PathVariable("id")Long id, @RequestBody SellCommand sellCommand) {
        sellService.updateSell(sellCommand, id);
        walletService.updatePlusWalletMoney(sellCommand, id);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @DeleteMapping("/sellList/{id}")
    public ResponseEntity<?> deleteSell(@PathVariable("id")Long id) {
        walletService.canclePlusWalletMoney(id);
        sellService.deleteSell(id);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

}
