package com.community.hululuuuu.controller;

import com.community.hululuuuu.product.ProductCommand;
import com.community.hululuuuu.product.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 그냥 만들기 눌렀을 때
    @GetMapping("/productForm")
    public ModelAndView productForm() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("product/productForm");
        return mav;
    }

    @PostMapping("/productForm")
    public ModelAndView createProduct(ProductCommand productCommand) {
        productService.addProduct(productCommand);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/product/productList");
        return mav;
    }

    @GetMapping({"/productMod", "/productMod/"})
    public ModelAndView modProduct(@RequestParam(value = "id", defaultValue = "0") Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("product", productService.findProductById(id));
        mav.setViewName("product/productMod");
        return mav;
    }

    @GetMapping("/productList")
    public ModelAndView productList(@PageableDefault Pageable pageable) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("productList", productService.findProductList(pageable));
        mav.setViewName("product/productList");
        return mav;
    }



}
