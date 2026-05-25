package com.backend.shoppingmall.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    void 상품_생성() {
        // given
        String name = "상품A";
        Long price = 10000L;

        //when
        Long productId = productService.createProduct(name, price);

        //then
        assertThat(productId).isNotNull();
    }
}