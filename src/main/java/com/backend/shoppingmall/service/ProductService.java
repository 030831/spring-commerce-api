package com.backend.shoppingmall.service;

import com.backend.shoppingmall.entity.Product;
import com.backend.shoppingmall.exception.ProductNotFoundException;
import com.backend.shoppingmall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private Product findProductOrThrow(Long productId) {
        Product product = productRepository.findById(productId);
        if (product == null) {
            throw new ProductNotFoundException("해당 상품은 존재하지 않습니다.");
        }
        return product;
    }

    @Transactional
    public Long createProduct(String name, Long price) {
        Product product = new Product(name, price);
        productRepository.save(product);
        return product.getId();
    }

    @Transactional(readOnly = true)
    public Product findProduct(Long productId) {
        return findProductOrThrow(productId);
    }

    @Transactional(readOnly = true)
    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public void updateProduct(Long productId, String name, Long price) {
        Product product = findProductOrThrow(productId);
        product.changeInfo(name, price);
    }

    @Transactional
    public void deleteProduct(Long productId) {
        Product product = findProductOrThrow(productId);
        productRepository.delete(product);
    }
}