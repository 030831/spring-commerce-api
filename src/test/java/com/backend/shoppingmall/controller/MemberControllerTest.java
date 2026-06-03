package com.backend.shoppingmall.controller;

import com.backend.shoppingmall.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class MemberControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberService memberService;

    @Test
    void 회원_생성_API_성공() throws Exception {

        String requestBody = """
                {
                    "name":"홍길동",
                    "email":"test@gmail.com"
                }
                """;

        mockMvc.perform(
                        post("/api/members")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    void 회원_조회_API_성공() throws Exception {
        // given
        String name = "홍길동";
        String email = "test@gmail.com";
        Long memberId = memberService.createMember(name, email);

        // when & then
        mockMvc.perform(
                get("/api/members/{memberId}", memberId)
        ).andExpect(status().isOk());

    }
}