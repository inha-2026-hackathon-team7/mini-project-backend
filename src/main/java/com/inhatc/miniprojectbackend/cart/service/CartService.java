package com.inhatc.miniprojectbackend.cart.service;

import com.inhatc.miniprojectbackend.cart.dto.CartResponseDTO;
import com.inhatc.miniprojectbackend.cart.entity.Cart;
import com.inhatc.miniprojectbackend.cart.entity.CartItem;
import com.inhatc.miniprojectbackend.cart.repository.CartItemRepository;
import com.inhatc.miniprojectbackend.cart.repository.CartRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    // 장바구니 조회
    public CartResponseDTO getCart(String sessionId) {
        return cartRepository.findBySessionId(sessionId)
                .map(this::getCartResponse)
                .orElseGet(CartResponseDTO::empty);
    }

    private CartResponseDTO getCartResponse(Cart cart) {
        List<CartItem> cartItems = cartItemRepository
                .findAllByCartCartIdOrderByCartItemIdAsc(cart.getCartId());

        return CartResponseDTO.from(cart, cartItems);
    }
}
