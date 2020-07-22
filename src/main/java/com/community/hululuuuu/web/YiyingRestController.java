package com.community.hululuuuu.web;


import com.community.hululuuuu.service.wallet.WalletService;
import com.community.hululuuuu.service.yiying.YiyingService;
import com.community.hululuuuu.web.dto.BuySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@RestController
@RequestMapping("/yiying")
public class YiyingRestController {

    private final YiyingService yiyingService;
    private final WalletService walletService;

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
    public ModelAndView buySubmit(BuySaveRequestDto requestDto) {

        yiyingService.createBuyList(requestDto);

        walletService.minusWalletMoney(requestDto);

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

    @GetMapping({"/buySearchList", "/buySearchList/"})
    public ModelAndView searchSellList(@RequestParam(value = "start")String start, @RequestParam(value = "end")String end, @PageableDefault Pageable pageable) {
        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ISO_DATE);
        LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ISO_DATE);
        ModelAndView mav = new ModelAndView();
        mav.addObject("buyList", yiyingService.buySearchList(pageable, startDate, endDate));
        mav.setViewName("yiying/buyList");
        return mav;
    }

    @DeleteMapping("/buyList/{id}")
    public ResponseEntity<?> deleteBuy(@PathVariable("id")Long id) {
        walletService.cancelMinusWalletMoney(id);
        yiyingService.deleteBuy(id);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @PutMapping("/buyList/{id}")
    public ResponseEntity<?> updateSubmitBuy(@PathVariable("id") Long id,@RequestBody BuySaveRequestDto requestDto) {
        yiyingService.updateBuy(requestDto, id);
        walletService.updateMinusWalletMoney(requestDto, id);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

}
