package com.backend.shoppingmall.service;

import com.backend.shoppingmall.entity.Member;
import com.backend.shoppingmall.exception.MemberNotFoundException;
import com.backend.shoppingmall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long createMember(String name, String email) {
        Member member = new Member(name, email);
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional(readOnly = true)
    public Member findMember(Long memberId) {

        Member member = memberRepository.findById(memberId);
        if (member == null) {
            throw new MemberNotFoundException("존재하지 않는 회원입니다.");
        }

        return member;
    }

    @Transactional
    public void updateMember(Long memberId, String name, String email) {
        Member member = findMember(memberId);
        member.changeInfo(name, email);
    }
}
