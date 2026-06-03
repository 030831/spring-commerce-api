package com.backend.shoppingmall.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected Product() {
    }

    public Product(String name, Long price) {
        this.name = name;
        this.price = price;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public void changeInfo(String name, Long price) {
        this.name = name;
        this.price = price;
    }
}
