package com.backend.shoppingmall.controller;


import com.backend.shoppingmall.dto.order.OrderCreateRequest;
import com.backend.shoppingmall.dto.order.OrderResponse;
import com.backend.shoppingmall.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Long createOrder(@Valid @RequestBody OrderCreateRequest request) {
        return orderService.createOrder(request.getMemberId(), request.getProductId(), request.getQuantity());
    }

    @GetMapping("/{orderId}")
    public OrderResponse findOrder(@PathVariable Long orderId) {
        return new OrderResponse(orderService.findOrder(orderId));
    }
}
