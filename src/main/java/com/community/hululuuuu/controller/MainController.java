package com.community.hululuuuu.controller;

import com.community.hululuuuu.product.Product;
import com.community.hululuuuu.product.ProductService;
import com.community.hululuuuu.repository.YiyingRepository;
import com.community.hululuuuu.sell.Sell;
import com.community.hululuuuu.sell.SellService;
import com.community.hululuuuu.wallet.Wallet;
import com.community.hululuuuu.wallet.WalletService;
import com.community.hululuuuu.yiying.Yiying;
import com.community.hululuuuu.yiying.YiyingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    private YiyingService yiyingService;
    private SellService sellService;
    private ProductService productService;
    private WalletService walletService;

    public MainController(YiyingService yiyingService, SellService sellService, ProductService productService, WalletService walletService) {
        this.yiyingService = yiyingService;
        this.sellService = sellService;
        this.productService = productService;
        this.walletService = walletService;
    }

    @GetMapping("/main")
    public ModelAndView main() {

        ModelAndView mav = new ModelAndView();
        List<Yiying> buyTop5 = yiyingService.findTop5();
        List<Sell> sellTop3 = sellService.findSellByyTop3();
        Product toothPaste = productService.selectToothPaste();
        List<Wallet> wallet = walletService.findTop5FromWallet();
        Wallet walletMoney = walletService.nowWallet();

        mav.addObject("buyTop", buyTop5);
        mav.addObject("sellTop", sellTop3);
        mav.addObject("toothPaste", toothPaste);
        mav.addObject("nowWallet", walletMoney);
        mav.addObject("walletTop5", wallet);
        mav.setViewName("main");
        return mav;

    }


}
