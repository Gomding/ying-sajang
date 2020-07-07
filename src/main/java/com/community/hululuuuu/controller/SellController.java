package com.community.hululuuuu.controller;

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

@Controller
@RequestMapping("/sell")
public class SellController {

    private SellService sellService;
    private WalletService walletService;

    public SellController (SellService sellService, WalletService walletService) {
        this.sellService = sellService;
        this.walletService = walletService;
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
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/sell/sellList");
        return mav;
    }

    @GetMapping({"/sellMod", "/sellMod/"})
    public ModelAndView modSellForm(@RequestParam(value = "id", defaultValue = "0") Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("sell", sellService.findSellById(id));
        mav.setViewName("sell/sellMod");
        return mav;
    }


}
