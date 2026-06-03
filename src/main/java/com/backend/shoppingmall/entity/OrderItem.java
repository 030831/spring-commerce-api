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

    @Column(name = "order_price", nullable = false)
    private Long orderPrice;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "product_name", nullable = false)
    private String productName;

    protected OrderItem() {

    }

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.productName = product.getName();
        this.orderPrice = product.getPrice();
        this.quantity = quantity;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
