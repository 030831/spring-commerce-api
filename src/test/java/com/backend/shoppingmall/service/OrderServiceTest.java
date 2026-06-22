package com.backend.shoppingmall.service;

import com.backend.shoppingmall.entity.Order;
import com.backend.shoppingmall.entity.OrderStatus;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Autowired
    EntityManager em;

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

    @Test
    void 주문_단건_조회_테스트() {
        // given
        Long memberId = memberService.createMember("홍길동", "test@gmail.com");
        Long productId = productService.createProduct("바나나", 10000L);
        Long orderId = orderService.createOrder(memberId, productId, 1);

        // when
        Order findOrder = orderService.findOrder(orderId);

        // then
        assertThat(orderId).isEqualTo(findOrder.getId());
    }

    @Test
    void 주문_취소_테스트() {
        // given
        Long memberId = memberService.createMember("홍길동", "test@gmail.com");
        Long productId = productService.createProduct("바나나", 10000L);
        Long orderId = orderService.createOrder(memberId, productId, 1);

        // when
        orderService.cancelOrder(orderId);
        em.flush();
        em.clear();

        // then
        Order findOrder = orderService.findOrder(orderId);
        assertThat(findOrder.getOrderStatus()).isEqualTo(OrderStatus.CANCELED);
    }
}