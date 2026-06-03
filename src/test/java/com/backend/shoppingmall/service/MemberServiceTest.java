package com.backend.shoppingmall.service;

import com.backend.shoppingmall.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void 회원_생성_테스트() {
        // given
        String name = "홍길동";
        String email = "test@gmail.com";

        // when
        Long memberId = memberService.createMember(name, email);

        // then
        assertThat(memberId).isNotNull();
    }

    @Test
    void 회원_단건_조회_테스트() {
        // given
        String name = "김민수";
        String email = "test2@gmail.com";

        // when
        Long memberId = memberService.createMember(name, email);
        Member member = memberService.findMember(memberId);

        // then
        assertThat(name).isEqualTo(member.getName());
        assertThat(email).isEqualTo(member.getEmail());
    }
}