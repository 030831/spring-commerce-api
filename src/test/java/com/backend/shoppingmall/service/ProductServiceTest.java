package com.backend.shoppingmall.service;

import com.backend.shoppingmall.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
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

    @Test
    void 상품_목록_조회() {
        // given
        productService.createProduct("상품A", 10000L);
        productService.createProduct("상품B", 20000L);

        //when
        List<Product> products = productService.findProducts();

        //then
        assertThat(products).hasSize(2);

    }
}