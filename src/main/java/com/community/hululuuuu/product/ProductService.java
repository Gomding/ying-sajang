package com.community.hululuuuu.product;

import com.community.hululuuuu.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findProductList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());
        return productRepository.findAll(pageable);
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

}
