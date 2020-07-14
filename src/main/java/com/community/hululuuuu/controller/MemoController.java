package com.community.hululuuuu.controller;

import com.community.hululuuuu.memo.MemoService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/memo")
public class MemoController {

    private MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping("/memoList")
    public ModelAndView getMemoList(@PageableDefault(size = 5) Pageable pageable) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("memoList", memoService.findMemoList(pageable));
        mav.setViewName("memo/memoList");
        return mav;
    }

}
