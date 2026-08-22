package com.inhatc.miniprojectbackend.cart.service;

import com.inhatc.miniprojectbackend.cart.dto.CartItemCreateRequestDTO;
import com.inhatc.miniprojectbackend.cart.dto.CartResponseDTO;
import com.inhatc.miniprojectbackend.cart.entity.Cart;
import com.inhatc.miniprojectbackend.cart.entity.CartItem;
import com.inhatc.miniprojectbackend.cart.repository.CartItemRepository;
import com.inhatc.miniprojectbackend.cart.repository.CartRepository;
import com.inhatc.miniprojectbackend.global.exception.BusinessException;
import com.inhatc.miniprojectbackend.global.exception.ErrorCode;
import com.inhatc.miniprojectbackend.menu.entity.Menu;
import com.inhatc.miniprojectbackend.menu.repository.MenuRepository;
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
    private final MenuRepository menuRepository;

    // 장바구니 조회
    public CartResponseDTO getCart(String sessionId) {
        return cartRepository.findBySessionId(sessionId)
                .map(this::getCartResponse)
                .orElseGet(CartResponseDTO::empty);
    }

    // 장바구니 메뉴 추가
    @Transactional
    public CartResponseDTO addCartItem(String sessionId, CartItemCreateRequestDTO request) {
        validateAddCartItemRequest(request);

        Menu menu = menuRepository.findById(request.menuId())
                .orElseThrow(() -> new BusinessException(ErrorCode.MENU_NOT_FOUND));
        Cart cart = getOrCreateCart(sessionId, menu, request.clearExisting());

        cartItemRepository.findByCartCartIdAndMenuMenuId(cart.getCartId(), menu.getMenuId())
                .ifPresentOrElse(
                        cartItem -> cartItem.increaseQuantity(request.quantity()),
                        () -> cartItemRepository.save(CartItem.create(cart, menu, request.quantity()))
                );

        return getCartResponse(cart);
    }

    private void validateAddCartItemRequest(CartItemCreateRequestDTO request) {
        if (request.menuId() == null || request.quantity() <= 0) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST);
        }
    }

    private Cart getOrCreateCart(String sessionId, Menu menu, boolean clearExisting) {
        return cartRepository.findBySessionId(sessionId)
                .map(cart -> getReusableCart(cart, menu, clearExisting))
                .orElseGet(() -> cartRepository.save(Cart.create(sessionId, menu.getRestaurant())));
    }

    private Cart getReusableCart(Cart cart, Menu menu, boolean clearExisting) {
        if (cart.getRestaurant().getRestaurantId().equals(menu.getRestaurant().getRestaurantId())) {
            return cart;
        }

        if (!clearExisting) {
            throw new BusinessException(ErrorCode.CART_RESTAURANT_MISMATCH);
        }

        cartRepository.delete(cart);
        cartRepository.flush();

        return cartRepository.save(Cart.create(cart.getSessionId(), menu.getRestaurant()));
    }

    private CartResponseDTO getCartResponse(Cart cart) {
        List<CartItem> cartItems = cartItemRepository
                .findAllByCartCartIdOrderByCartItemIdAsc(cart.getCartId());

        return CartResponseDTO.from(cart, cartItems);
    }
}
