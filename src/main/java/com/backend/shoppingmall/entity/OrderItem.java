package com.backend.shoppingmall.entity;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "order_items")
@Getter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "order_price")
    private int orderPrice;

    private int quantity;

    public void setOrder(Order order) {
        this.order = order;
    }
}
