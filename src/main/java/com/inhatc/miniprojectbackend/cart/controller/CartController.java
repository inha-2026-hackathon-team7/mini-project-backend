package com.inhatc.miniprojectbackend.cart.controller;

import com.inhatc.miniprojectbackend.cart.dto.CartItemCreateRequestDTO;
import com.inhatc.miniprojectbackend.cart.dto.CartItemQuantityUpdateRequestDTO;
import com.inhatc.miniprojectbackend.cart.dto.CartResponseDTO;
import com.inhatc.miniprojectbackend.cart.service.CartService;
import com.inhatc.miniprojectbackend.global.session.SessionCookieManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Cart", description = "장바구니 관련 API (세션 쿠키 기반)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final SessionCookieManager sessionCookieManager;

    @Operation(summary = "장바구니 조회", description = "현재 세션의 장바구니를 조회합니다.")
    @GetMapping
    public ResponseEntity<CartResponseDTO> getCart(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        String sessionId = sessionCookieManager.getOrCreateSessionId(request, response);
        return ResponseEntity.ok(cartService.getCart(sessionId));
    }

    @Operation(summary = "장바구니 메뉴 추가", description = "장바구니에 메뉴를 추가합니다.")
    @PostMapping("/items")
    public ResponseEntity<CartResponseDTO> addCartItem(
            @RequestBody CartItemCreateRequestDTO cartItemCreateRequestDTO,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        String sessionId = sessionCookieManager.getOrCreateSessionId(request, response);
        return ResponseEntity.ok(cartService.addCartItem(sessionId, cartItemCreateRequestDTO));
    }

    @Operation(summary = "장바구니 메뉴 수량 변경", description = "장바구니에 담긴 메뉴의 수량을 변경합니다.")
    @PatchMapping("/items/{cartItemId}")
    public ResponseEntity<CartResponseDTO> updateCartItemQuantity(
            @Parameter(description = "장바구니 항목 ID", example = "1") @PathVariable Long cartItemId,
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

    @Operation(summary = "장바구니 메뉴 삭제", description = "장바구니에서 특정 메뉴를 삭제합니다.")
    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<CartResponseDTO> removeCartItem(
            @Parameter(description = "장바구니 항목 ID", example = "1") @PathVariable Long cartItemId,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        String sessionId = sessionCookieManager.getOrCreateSessionId(request, response);
        return ResponseEntity.ok(cartService.removeCartItem(sessionId, cartItemId));
    }

    @Operation(summary = "장바구니 전체 삭제", description = "현재 세션의 장바구니를 전체 비웁니다.")
    @DeleteMapping
    public ResponseEntity<CartResponseDTO> clearCart(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        String sessionId = sessionCookieManager.getOrCreateSessionId(request, response);
        return ResponseEntity.ok(cartService.clearCart(sessionId));
    }
}
