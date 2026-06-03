package com.backend.shoppingmall.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 255)
    private String email;


    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected Member() {
    }

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public void changeInfo(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
