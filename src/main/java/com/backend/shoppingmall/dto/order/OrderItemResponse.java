package com.backend.shoppingmall.dto.order;

import com.backend.shoppingmall.entity.OrderItem;
import lombok.Getter;

@Getter
public class OrderItemResponse {

    private String productName;
    private Long orderPrice;
    private int quantity;

    public OrderItemResponse(OrderItem orderItem) {
        this.productName = orderItem.getProductName();
        this.orderPrice = orderItem.getOrderPrice();
        this.quantity = orderItem.getQuantity();
    }
}
