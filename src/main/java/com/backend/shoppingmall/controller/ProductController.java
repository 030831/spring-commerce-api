package com.backend.shoppingmall.controller;


import com.backend.shoppingmall.dto.product.ProductCreateRequest;
import com.backend.shoppingmall.dto.product.ProductResponse;
import com.backend.shoppingmall.dto.product.ProductUpdateRequest;
import com.backend.shoppingmall.entity.Product;
import com.backend.shoppingmall.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Long createProduct(@Valid @RequestBody ProductCreateRequest request) {
        return productService.createProduct(request.getName(), request.getPrice());
    }

    @GetMapping("/{productId}")
    public ProductResponse findProduct(@PathVariable Long productId) {
        return new ProductResponse(productService.findProduct(productId));
    }

    @GetMapping
    public List<ProductResponse> findProducts() {
        return productService.findProducts().stream()
                .map(ProductResponse::new)
                .toList();
    }

    @PatchMapping("/{productId}")
    public void updateProduct(@PathVariable Long productId, @Valid @RequestBody ProductUpdateRequest request) {
        productService.updateProduct(productId, request.getName(), request.getPrice());
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }
}
