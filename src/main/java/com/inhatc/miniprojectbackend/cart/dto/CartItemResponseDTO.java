package com.inhatc.miniprojectbackend.cart.dto;

import com.inhatc.miniprojectbackend.cart.entity.CartItem;
import com.inhatc.miniprojectbackend.menu.entity.Menu;

public record CartItemResponseDTO(
        Long cartItemId,
        Long menuId,
        String menuName,
        int menuPrice,
        String imageUrl,
        int quantity,
        int itemTotalAmount
) {

    public static CartItemResponseDTO from(CartItem cartItem) {
        Menu menu = cartItem.getMenu();

        return new CartItemResponseDTO(
                cartItem.getCartItemId(),
                menu.getMenuId(),
                menu.getName(),
                menu.getPrice(),
                menu.getImageUrl(),
                cartItem.getQuantity(),
                menu.getPrice() * cartItem.getQuantity()
        );
    }
}
