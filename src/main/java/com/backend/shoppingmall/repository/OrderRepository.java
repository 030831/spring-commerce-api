package com.backend.shoppingmall.repository;

import com.backend.shoppingmall.entity.Order;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findByMemberId(Long memberId) {
        return em.createQuery("select o from Order o where o.member.id = :memberId", Order.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
