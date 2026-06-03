package com.backend.shoppingmall.dto.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderCreateRequest {

    @NotNull(message = "회원 id는 필수입니다.")
    private Long memberId;

    @NotNull(message = "상품 id는 필수입니다.")
    private Long productId;

    @Positive(message = "주문 수량은 1개 이상이어야 합니다.")
    @NotNull(message = "주문 수량은 필수 입니다.")
    private Integer quantity;
}
