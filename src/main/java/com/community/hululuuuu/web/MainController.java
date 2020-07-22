package com.community.hululuuuu.web;

import com.community.hululuuuu.domain.product.Product;
import com.community.hululuuuu.service.product.ProductService;
import com.community.hululuuuu.domain.sell.Sell;
import com.community.hululuuuu.service.sell.SellService;
import com.community.hululuuuu.domain.wallet.Wallet;
import com.community.hululuuuu.service.wallet.WalletService;
import com.community.hululuuuu.domain.yiying.Yiying;
import com.community.hululuuuu.service.yiying.YiyingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final YiyingService yiyingService;
    private final SellService sellService;
    private final ProductService productService;
    private final WalletService walletService;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/index");
        return mav;
    }

    @GetMapping("/main")
    public ModelAndView main() {

        ModelAndView mav = new ModelAndView();
        List<Yiying> buyTop5 = yiyingService.findTop5();
        List<Sell> sellTop3 = sellService.findSellByTop3();
        Product toothPaste = productService.selectToothPaste();
        List<Wallet> wallet = walletService.findTop5FromWallet();
        Wallet walletMoney = walletService.nowWallet();
        int oneMonthProfit = sellService.sum1MonthProfit();
        int oneMonthSpendMoney = yiyingService.sum1MonthSpendMoney();

        mav.addObject("oneMonthProfit", oneMonthProfit);
        mav.addObject("oneMonthSpendMoney", oneMonthSpendMoney);
        mav.addObject("buyTop", buyTop5);
        mav.addObject("sellTop", sellTop3);
        mav.addObject("toothPaste", toothPaste);
        mav.addObject("nowWallet", walletMoney);
        mav.addObject("walletTop5", wallet);
        mav.setViewName("main");
        return mav;

    }


}
