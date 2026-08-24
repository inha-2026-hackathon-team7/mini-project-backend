package com.inhatc.miniprojectbackend.order.dto;

import com.inhatc.miniprojectbackend.cart.entity.CartItem;
import com.inhatc.miniprojectbackend.menu.entity.Menu;

public record CheckoutItemResponseDTO(
        Long cartItemId,
        Long menuId,
        String menuName,
        int menuPrice,
        String imageUrl,
        int quantity,
        int itemTotalAmount
) {

    public static CheckoutItemResponseDTO from(CartItem cartItem) {
        Menu menu = cartItem.getMenu();

        return new CheckoutItemResponseDTO(
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
