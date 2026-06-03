package com.backend.shoppingmall.controller;

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
@AutoConfigureMockMvc
@Transactional
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void 상품_생성_API_성공_테스트() throws Exception {
        // given

        String requestBody = """
                {
                    "name": "상품A",
                    "price" : 10000
                }
                """;

        // when & then

        mockMvc.perform(
                        post("/api/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isOk());
    }

    @Test
    void 상품_생성_API_상품명_검증_실패() throws Exception {
        String requestBody = """
                {
                    "name" : "",
                    "price" : 1000
                }
                """;
        mockMvc.perform(
                        post("/api/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void 없는_상품_id_조회_API_404_테스트() throws Exception {
        mockMvc.perform(
                        get("/api/products/{productId}", 999L)
                )
                .andExpect(status().isNotFound());
    }
}