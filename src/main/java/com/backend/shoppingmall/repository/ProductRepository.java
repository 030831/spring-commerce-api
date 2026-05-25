package com.backend.shoppingmall.repository;

import com.backend.shoppingmall.entity.Product;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor //final 필드에 대해 생성자를 자동으로 생성해 스프링이 의존성 주입을 하게 해준다.
public class ProductRepository {

    private final EntityManager em;

    public void save(Product product) {
        em.persist(product);
    }
}
