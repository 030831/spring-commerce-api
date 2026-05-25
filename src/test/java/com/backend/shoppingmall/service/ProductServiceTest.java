package com.backend.shoppingmall.service;

import com.backend.shoppingmall.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@Slf4j
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

    @Test
    void 상품_단건_조회() {
        // given
        String name = "상품B";
        Long price = 20000L;
        Long productId = productService.createProduct(name, price);

        //when
        Product product = productService.findProduct(productId);

        //then
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getPrice()).isEqualTo(price);
    }
}