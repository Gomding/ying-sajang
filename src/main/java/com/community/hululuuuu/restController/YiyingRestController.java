package com.community.hululuuuu.restController;


import com.community.hululuuuu.wallet.WalletService;
import com.community.hululuuuu.yiying.BuyCommand;
import com.community.hululuuuu.yiying.YiyingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/yiying")
public class YiyingRestController {

    private YiyingService yiyingService;
    private WalletService walletService;

    public YiyingRestController (YiyingService yiyingService, WalletService walletService) {
        this.yiyingService = yiyingService;
        this.walletService = walletService;
    }

    @DeleteMapping("/buyList/{id}")
    public ResponseEntity<?> deleteBuy(@PathVariable("id")Long id) {
        walletService.cancleMinusWalletMoney(id);
        yiyingService.deleteBuy(id);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @PutMapping("/buyList/{id}")
    public ResponseEntity<?> updateSubmitBuy(@PathVariable("id") Long id,@RequestBody BuyCommand buyCommand) {
        yiyingService.updateBuy(buyCommand, id);
        walletService.updateMinusWalletMoney(buyCommand, id);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

}
