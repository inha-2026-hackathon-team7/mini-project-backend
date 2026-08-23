package com.inhatc.miniprojectbackend.cart.controller;

import com.inhatc.miniprojectbackend.cart.dto.CartItemCreateRequestDTO;
import com.inhatc.miniprojectbackend.cart.dto.CartItemQuantityUpdateRequestDTO;
import com.inhatc.miniprojectbackend.cart.dto.CartResponseDTO;
import com.inhatc.miniprojectbackend.cart.service.CartService;
import com.inhatc.miniprojectbackend.global.session.SessionCookieManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final SessionCookieManager sessionCookieManager;

    // 장바구니 조회
    @GetMapping
    public ResponseEntity<CartResponseDTO> getCart(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        String sessionId = sessionCookieManager.getOrCreateSessionId(request, response);
        return ResponseEntity.ok(cartService.getCart(sessionId));
    }

    // 장바구니 메뉴 추가
    @PostMapping("/items")
    public ResponseEntity<CartResponseDTO> addCartItem(
            @RequestBody CartItemCreateRequestDTO cartItemCreateRequestDTO,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        String sessionId = sessionCookieManager.getOrCreateSessionId(request, response);
        return ResponseEntity.ok(cartService.addCartItem(sessionId, cartItemCreateRequestDTO));
    }

    // 장바구니 메뉴 수량 변경
    @PatchMapping("/items/{cartItemId}")
    public ResponseEntity<CartResponseDTO> updateCartItemQuantity(
            @PathVariable Long cartItemId,
            @RequestBody CartItemQuantityUpdateRequestDTO cartItemQuantityUpdateRequestDTO,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        String sessionId = sessionCookieManager.getOrCreateSessionId(request, response);
        return ResponseEntity.ok(cartService.updateCartItemQuantity(
                sessionId,
                cartItemId,
                cartItemQuantityUpdateRequestDTO
        ));
    }
}
