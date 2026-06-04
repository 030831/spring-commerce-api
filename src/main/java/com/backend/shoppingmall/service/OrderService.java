package com.backend.shoppingmall.service;

import com.backend.shoppingmall.entity.Member;
import com.backend.shoppingmall.entity.Order;
import com.backend.shoppingmall.entity.OrderItem;
import com.backend.shoppingmall.entity.Product;
import com.backend.shoppingmall.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberService memberService;
    private final ProductService productService;

    @Transactional
    public Long createOrder(Long memberId, Long productId, int quantity) {
        Member member = memberService.findMember(memberId);
        Product product = productService.findProduct(productId);
        Order order = new Order(member);
        OrderItem orderItem = new OrderItem(product, quantity);

        order.addOrderItem(orderItem);
        orderRepository.save(order);
        return order.getId();
    }

    @Transactional(readOnly = true)
    public List<Order> findOrdersByMemberId(Long memberId) {
        memberService.findMember(memberId);
        return orderRepository.findByMemberId(memberId);
    }
}
