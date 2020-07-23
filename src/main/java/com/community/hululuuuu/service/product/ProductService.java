package com.community.hululuuuu.service.product;

import com.community.hululuuuu.domain.product.Product;
import com.community.hululuuuu.domain.product.ProductRepository;
import com.community.hululuuuu.domain.sell.Sell;
import com.community.hululuuuu.myComponent.PageableDefault;
import com.community.hululuuuu.web.dto.ProductRequestDto;
import com.community.hululuuuu.web.dto.SellRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> findProductList(Pageable pageable) {
        pageable = PageableDefault.setPageable(pageable);
        return productRepository.findAllByOrderByProductModdateDesc(pageable);
    }

    @Transactional
    public Long addProduct (ProductRequestDto requestDto) {
        return productRepository.save(requestDto.toEntity()).getProductId();
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(new Product());
    }

    @Transactional
    public void updateProduct(ProductRequestDto requestDto, Long id) {
        Product persistProduct = productRepository.getOne(id);
        persistProduct.update(requestDto.getName(),
                requestDto.getAmount(),
                requestDto.getPrice(),
                requestDto.getCostprice(),
                LocalDateTime.now());
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product selectToothPaste() {
        return productRepository.findByProductName("치약");
    }

    @Transactional
    public void updateProductAmountBecauseSell(SellRequestDto requestDto) {
        Product product = productRepository.findByProductName(requestDto.getProduct());
        product.updateProductAmount(product.getProductAmount() - requestDto.getAmount());
    }

    @Transactional
    public void updateProductAmountAfterDelSell(Sell sell) {
        Product product = productRepository.findByProductName(sell.getSellProduct());
        product.updateProductAmountAfterDelSell(product.getProductAmount() + sell.getSellAmount());
    }

    @Transactional
    public void updateProductAmountAfterUpdateSell(SellRequestDto requestDto, int oldAmount) {
        Product product = productRepository.findByProductName(requestDto.getProduct());
        product.updateProductAmountAfterUpdateSell(requestDto.getAmount(), oldAmount);
    }

    @Transactional
    public Page<Product> searchProductList(Pageable pageable, String productName) {
        pageable = PageableDefault.setPageable(pageable);
        return productRepository.findByProductName(pageable, productName);
    }

}
