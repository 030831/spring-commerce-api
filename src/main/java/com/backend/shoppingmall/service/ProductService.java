package com.backend.shoppingmall.service;

import com.backend.shoppingmall.entity.Product;
import com.backend.shoppingmall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Long createProduct(String name, Long price) {
        Product product = new Product(name, price);
        productRepository.save(product);
        return product.getId();
    }
}
