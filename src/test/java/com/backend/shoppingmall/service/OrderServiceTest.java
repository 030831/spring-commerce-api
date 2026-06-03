package com.backend.shoppingmall.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    MemberService memberService;

    @Autowired
    ProductService productService;

    @Test
    void 주문_생성_테스트() {
        // given
        Long memberId = memberService.createMember("홍길동", "test@gmail.com");
        Long productId = productService.createProduct("바나나", 10000L);
        int quantity = 10;

        // when
        Long orderId = orderService.createOrder(memberId, productId, quantity);

        // then
        assertThat(orderId).isNotNull();
    }
}