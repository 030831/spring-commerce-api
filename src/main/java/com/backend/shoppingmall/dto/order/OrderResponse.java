package com.backend.shoppingmall.dto.order;

import com.backend.shoppingmall.entity.Order;
import com.backend.shoppingmall.entity.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderResponse {

    private Long orderId;
    private OrderStatus orderStatus;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> items;

    public OrderResponse(Order order) {
        this.orderId = order.getId();
        this.orderStatus = order.getOrderStatus();
        this.createdAt = order.getCreatedAt();
        this.items = order.getOrderItems().stream()
                .map(OrderItemResponse::new).toList();
    }
}
