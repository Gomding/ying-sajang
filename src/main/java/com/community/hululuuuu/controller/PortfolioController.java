package com.community.hululuuuu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/portfolio")
public class PortfolioController {

    @GetMapping("/profile")
    public ModelAndView profile() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("portfolio/profile");
        return mav;
    }

}
