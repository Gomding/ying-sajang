package com.community.hululuuuu.restController;

import com.community.hululuuuu.product.ProductCommand;
import com.community.hululuuuu.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    private ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @PutMapping("/productMod/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id")Long id, @RequestBody ProductCommand productCommand) {
        productService.updateProduct(productCommand, id);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @DeleteMapping("/productList/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id")Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
