package com.backend.shoppingmall.controller;


import com.backend.shoppingmall.dto.product.ProductCreateRequest;
import com.backend.shoppingmall.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Long createProduct(@Valid @RequestBody  ProductCreateRequest request) {
        return productService.createProduct(request.getName(), request.getPrice());
    }
}
