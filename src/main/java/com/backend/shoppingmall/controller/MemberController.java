package com.backend.shoppingmall.controller;

import com.backend.shoppingmall.dto.member.MemberCreateRequest;
import com.backend.shoppingmall.dto.member.MemberResponse;
import com.backend.shoppingmall.dto.order.OrderResponse;
import com.backend.shoppingmall.service.MemberService;
import com.backend.shoppingmall.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final OrderService orderService;

    @PostMapping
    public Long createMember(@Valid @RequestBody MemberCreateRequest request) {
        return memberService.createMember(request.getName(), request.getEmail());
    }

    @GetMapping("/{memberId}")
    public MemberResponse findMember(@PathVariable Long memberId) {
        return new MemberResponse(memberService.findMember(memberId));
    }

    @GetMapping("/{memberId}/orders")
    public List<OrderResponse> getMemberOrders(@PathVariable Long memberId) {
        return orderService.findOrdersByMemberId(memberId)
                .stream()
                .map(OrderResponse::new)
                .toList();
    }
}
