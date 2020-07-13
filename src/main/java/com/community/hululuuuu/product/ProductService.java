package com.community.hululuuuu.product;

import com.community.hululuuuu.repository.ProductRepository;
import com.community.hululuuuu.sell.Sell;
import com.community.hululuuuu.sell.SellCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findProductList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());
        return productRepository.findAllByOrderByProductModdateDesc(pageable);
    }

    public void addProduct (ProductCommand productCommand) {
        productRepository.save(Product.builder()
        .productName(productCommand.getName())
        .productAmount(productCommand.getAmount())
        .productPrice(productCommand.getPrice())
        .productCostprice(productCommand.getCostprice())
        .productModdate(LocalDateTime.now())
        .build());
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(new Product());
    }

    public void updateProduct(ProductCommand productCommand, Long id) {
        Product presistProdict = productRepository.getOne(id);
        presistProdict.update(productCommand);
        productRepository.save(presistProdict);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product selectToothPaste() {
        return productRepository.findByProductName("치약");
    }

    public void updateProductAmount(SellCommand sellCommand) {
        Product product = productRepository.findByProductName(sellCommand.getProduct());
        product.setProductAmount(product.getProductAmount() - sellCommand.getAmount());
        productRepository.save(product);
    }

    public void afterDelSellAmount(Sell sell) {
        Product product = productRepository.findByProductName(sell.getSellProduct());
        product.setProductAmount(product.getProductAmount() + sell.getSellAmount());
        productRepository.save(product);
    }

    public void afterUpdateSellAmount(SellCommand sellCommand, int oldAmount) {
        Product product = productRepository.findByProductName(sellCommand.getProduct());
        int sellAmount = sellCommand.getAmount();
        int diffAmount = oldAmount - sellAmount;
        product.setProductAmount(product.getProductAmount() + diffAmount);
        productRepository.save(product);
    }

    public Page<Product> searchProductList(Pageable pageable, String productName) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());
        return productRepository.findByProductName(pageable, productName);
    }

}
