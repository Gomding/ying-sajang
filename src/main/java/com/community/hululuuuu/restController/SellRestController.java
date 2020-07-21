package com.community.hululuuuu.restController;

import com.community.hululuuuu.product.ProductService;
import com.community.hululuuuu.sell.SellCommand;
import com.community.hululuuuu.sell.SellService;
import com.community.hululuuuu.wallet.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/sell")
public class SellRestController {

    private SellService sellService;
    private WalletService walletService;
    private ProductService productService;

    public SellRestController(SellService sellService, WalletService walletService, ProductService productService) {
        this.sellService = sellService;
        this.walletService = walletService;
        this.productService = productService;
    }

    @PutMapping("/sellList/{id}")
    public ResponseEntity<?> updateSell(@PathVariable("id")Long id, @RequestBody SellCommand sellCommand) {
        productService.afterUpdateSellAmount(sellCommand, sellService.sellFindById(id).getSellAmount());
        walletService.updatePlusWalletMoney(sellCommand, id);
        sellService.updateSell(sellCommand, id);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @DeleteMapping("/sellList/{id}")
    public ResponseEntity<?> deleteSell(@PathVariable("id")Long id) {
        productService.afterDelSellAmount(sellService.sellFindById(id));
        walletService.canclePlusWalletMoney(id);
        sellService.deleteSell(id);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

}
