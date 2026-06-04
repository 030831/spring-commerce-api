package com.backend.shoppingmall.controller;

import com.backend.shoppingmall.entity.Order;
import com.backend.shoppingmall.service.MemberService;
import com.backend.shoppingmall.service.OrderService;
import com.backend.shoppingmall.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    MemberService memberService;

    @Autowired
    ProductService productService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    OrderService orderService;

    @Test
    void 주문_생성_API_성공() throws Exception {
        // given
        Long memberId = memberService.createMember("홍길동", "test@gmail.com");
        Long productId = productService.createProduct("바나나", 1000L);
        String requestBody = """
                {
                    "memberId":%d,
                    "productId":%d,
                    "quantity":1
                }
                """.formatted(memberId, productId);

        mockMvc.perform(
                        post("/api/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    void 회원별_주문_목록_조회_테스트() {
        // given
        Long memberId = memberService.createMember("홍길동", "test@gmail.com");
        Long productId = productService.createProduct("바나나", 10000L);
        int quantity = 10;
        orderService.createOrder(memberId, productId, quantity);

        // when
        List<Order> orders = orderService.findOrdersByMemberId(memberId);

        // then
        assertThat(orders).hasSize(1);
    }
}