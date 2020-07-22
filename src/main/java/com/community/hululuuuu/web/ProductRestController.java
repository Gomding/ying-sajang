package com.community.hululuuuu.web;

import com.community.hululuuuu.service.product.ProductService;
import com.community.hululuuuu.web.dto.ProductRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("/productForm")
    public ModelAndView productForm() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("product/productForm");
        return mav;
    }

    @PostMapping("/productForm")
    public ModelAndView createProduct(ProductRequestDto requestDto) {
        productService.addProduct(requestDto);
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

    @GetMapping({"/productSearchList", "/productSearchList/"})
    public ModelAndView searchProductList(@PageableDefault Pageable pageable, @RequestParam("productName")String productName) {
        ModelAndView mav = new ModelAndView();

        if (productName.equals("") || productName.isEmpty()) {
            mav.setViewName("redirect:/product/productList");
        }
        else {
            mav.addObject("productList", productService.searchProductList(pageable, productName));
            mav.setViewName("product/productList");
        }
        return mav;
    }

    @PutMapping("/productMod/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id")Long id, @RequestBody ProductRequestDto requestDto) {
        productService.updateProduct(requestDto, id);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @DeleteMapping("/productList/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id")Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
