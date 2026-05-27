package com.backend.shoppingmall.dto.product;

import com.backend.shoppingmall.entity.Product;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductResponse {

    private Long id;
    private String name;
    private Long price;
    private LocalDateTime createdAt;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.createdAt = product.getCreatedAt();
    }
}
