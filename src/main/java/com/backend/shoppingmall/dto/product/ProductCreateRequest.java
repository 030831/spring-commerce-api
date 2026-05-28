package com.backend.shoppingmall.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductCreateRequest {

    @NotBlank(message = "상품명은 필수입니다.")
    private String name;

    @NotNull(message = "상품 가격은 필수입니다.")
    @Positive(message = "상품 가격은 0보다 커야 합니다.")
    private Long price;
}
