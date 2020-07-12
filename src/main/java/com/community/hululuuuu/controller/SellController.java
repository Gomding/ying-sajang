package com.community.hululuuuu.controller;

import com.community.hululuuuu.product.ProductService;
import com.community.hululuuuu.sell.SellCommand;
import com.community.hululuuuu.sell.SellService;
import com.community.hululuuuu.wallet.WalletService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/sell")
public class SellController {

    private SellService sellService;
    private WalletService walletService;
    private ProductService productService;

    public SellController (SellService sellService, WalletService walletService, ProductService productService) {
        this.sellService = sellService;
        this.walletService = walletService;
        this.productService = productService;
    }


    @GetMapping("/sellList")
    public ModelAndView sellList(@PageableDefault Pageable pageable) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("sellList", sellService.findSellList(pageable));
        mav.setViewName("sell/sellList");
        return mav;
    }

    @GetMapping("/sellForm")
    public ModelAndView sellForm() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("sell/sellForm");
        return mav;
    }

    @PostMapping("/sellForm")
    public ModelAndView submitSellForm(SellCommand sellCommand) {
        sellService.createSell(sellCommand);
        walletService.plusWalletMoney(sellCommand);
        productService.updateProductAmount(sellCommand);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/sell/sellList");
        return mav;
    }

    @GetMapping({"/sellMod", "/sellMod/"})
    public ModelAndView modSellForm(@RequestParam(value = "id", defaultValue = "0") Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("sell", sellService.sellFindById(id));
        mav.setViewName("sell/sellMod");
        return mav;
    }

    @GetMapping({"/sellSearchList", "/sellSearchList/"})
    public ModelAndView searchSellList(@RequestParam(value = "start")String start, @RequestParam(value = "end")String end, @PageableDefault Pageable pageable) {
        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ISO_DATE);
        LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ISO_DATE);
        ModelAndView mav = new ModelAndView();
        mav.addObject("sellList", sellService.searchSellList(pageable, startDate, endDate));
        mav.setViewName("sell/sellList");
        return mav;
    }


}
