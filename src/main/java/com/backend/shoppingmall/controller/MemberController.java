package com.backend.shoppingmall.controller;

import com.backend.shoppingmall.dto.member.MemberCreateRequest;
import com.backend.shoppingmall.dto.member.MemberResponse;
import com.backend.shoppingmall.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public Long createMember(@Valid @RequestBody MemberCreateRequest request) {
        return memberService.createMember(request.getName(), request.getEmail());
    }

    @GetMapping("/{memberId}")
    public MemberResponse findMember(@PathVariable Long memberId) {
        return new MemberResponse(memberService.findMember(memberId));
    }
}
