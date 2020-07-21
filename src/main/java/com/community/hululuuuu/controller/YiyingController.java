package com.community.hululuuuu.controller;

import com.community.hululuuuu.wallet.WalletService;
import com.community.hululuuuu.yiying.BuyCommand;
import com.community.hululuuuu.yiying.YiyingService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/yiying")
public class YiyingController {

    private YiyingService yiyingService;
    private WalletService walletService;

    public YiyingController(YiyingService yiyingService, WalletService walletService) {
        this.yiyingService = yiyingService;
        this.walletService = walletService;
    }

    @GetMapping("/buyList")
    public ModelAndView buyList(@PageableDefault Pageable pageable) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("buyList", yiyingService.findBuyList(pageable));
        mav.setViewName("yiying/buyList");
        return mav;
    }

    @GetMapping("/buyForm")
    public ModelAndView buyForm() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("yiying/buyForm");
        return mav;
    }

    @PostMapping("/buyForm")
    public ModelAndView buySubmit(@Valid BuyCommand buyCommand) {

        yiyingService.createBuyList(buyCommand);

        walletService.minusWalletMoney(buyCommand);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/yiying/buyList");
        return mav;
    }

    @GetMapping({"/buyDetail", "/buyDetail/"})
    public ModelAndView buyDetail(@RequestParam(value = "id")Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("buy", yiyingService.findById(id));
        mav.setViewName("yiying/buyDetail");
        return mav;
    }

    @GetMapping({"/buyMod", "/buyMod/"})
    public ModelAndView buyUpdate(@RequestParam(value = "id")Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("buyCommand", yiyingService.findById(id));
        mav.setViewName("yiying/buyMod");
        return mav;
    }

    @GetMapping({"/buyList", "/buySearchList/"})
    public ModelAndView searchSellList(@RequestParam(value = "start")String start, @RequestParam(value = "end")String end, @PageableDefault Pageable pageable) {
        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ISO_DATE);
        LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ISO_DATE);
        ModelAndView mav = new ModelAndView();
        mav.addObject("buyList", yiyingService.buySearchList(pageable, startDate, endDate));
        mav.setViewName("yiying/buyList");
        return mav;
    }

}
