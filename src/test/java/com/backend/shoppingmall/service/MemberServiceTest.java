package com.backend.shoppingmall.service;

import com.backend.shoppingmall.entity.Member;
import jakarta.persistence.EntityManager;
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

    @Autowired
    EntityManager em;

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

    @Test
    void 회원_정보_수정_테스트() {
        // given
        Long memberId = memberService.createMember("홍길동", "test@gmail.com");

        // when
        String updateName = "김민수";
        String updateEmail = "test2@gmail.com";
        memberService.updateMember(memberId, updateName, updateEmail);
        em.flush();
        em.clear();

        // then
        Member findMember = memberService.findMember(memberId);
        assertThat(updateName).isEqualTo(findMember.getName());
        assertThat(updateEmail).isEqualTo(findMember.getEmail());
    }
}