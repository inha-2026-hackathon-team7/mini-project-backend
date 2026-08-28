package com.inhatc.miniprojectbackend.cart.dto;

import com.inhatc.miniprojectbackend.cart.entity.Cart;
import com.inhatc.miniprojectbackend.cart.entity.CartItem;
import com.inhatc.miniprojectbackend.restaurant.entity.Restaurant;
import java.util.List;

public record CartResponseDTO(
        Long cartId,
        Long restaurantId,
        String restaurantName,
        int minimumOrderAmount,
        int deliveryFee,
        int subtotal,
        int totalAmount,
        int remainingAmount,
        boolean canOrder,
        List<CartItemResponseDTO> items
) {

    public static CartResponseDTO empty() {
        return new CartResponseDTO(
                null,
                null,
                null,
                0,
                0,
                0,
                0,
                0,
                false,
                List.of()
        );
    }

    public static CartResponseDTO from(Cart cart, List<CartItem> cartItems) {
        Restaurant restaurant = cart.getRestaurant();
        List<CartItemResponseDTO> items = cartItems.stream()
                .map(CartItemResponseDTO::from)
                .toList();
        int subtotal = items.stream()
                .mapToInt(CartItemResponseDTO::itemTotalAmount)
                .sum();
        int totalAmount = subtotal + restaurant.getDeliveryFee();
        int remainingAmount = Math.max(restaurant.getMinimumOrderAmount() - subtotal, 0);

        return new CartResponseDTO(
                cart.getCartId(),
                restaurant.getRestaurantId(),
                restaurant.getName(),
                restaurant.getMinimumOrderAmount(),
                restaurant.getDeliveryFee(),
                subtotal,
                totalAmount,
                remainingAmount,
                subtotal >= restaurant.getMinimumOrderAmount(),
                items
        );
    }
}
