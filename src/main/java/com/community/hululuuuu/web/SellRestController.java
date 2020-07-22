package com.community.hululuuuu.web;

import com.community.hululuuuu.service.product.ProductService;
import com.community.hululuuuu.service.sell.SellService;
import com.community.hululuuuu.service.wallet.WalletService;
import com.community.hululuuuu.web.dto.SellRequestDto;
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
@RequestMapping("/sell")
public class SellRestController {

    private final SellService sellService;
    private final WalletService walletService;
    private final ProductService productService;

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
    public ModelAndView submitSellForm(SellRequestDto requestDto) {
        sellService.createSell(requestDto);
        walletService.plusWalletMoney(requestDto);
        productService.updateProductAmountBecauseSell(requestDto);
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

    @PutMapping("/sellList/{id}")
    public ResponseEntity<?> updateSell(@PathVariable("id")Long id, @RequestBody SellRequestDto requestDto) {
        productService.updateProductAmountAfterUpdateSell(requestDto, sellService.sellFindById(id).getSellAmount());
        walletService.updatePlusWalletMoney(requestDto, id);
        sellService.updateSell(requestDto, id);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @DeleteMapping("/sellList/{id}")
    public ResponseEntity<?> deleteSell(@PathVariable("id")Long id) {
        productService.updateProductAmountAfterDelSell(sellService.sellFindById(id));
        walletService.cancelPlusWalletMoney(id);
        sellService.deleteSell(id);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

}
