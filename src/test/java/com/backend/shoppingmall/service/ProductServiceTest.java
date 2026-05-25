package com.backend.shoppingmall.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    void 상품_생셩() {
        // given
        String name = "상품A";
        Long price = 10000L;

        //when
        Long productId = productService.createProduct(name, price);

        //then
        Assertions.assertThat(productId).isNotNull();
    }
}