package com.inhatc.miniprojectbackend.order.dto;

import com.inhatc.miniprojectbackend.cart.entity.Cart;
import com.inhatc.miniprojectbackend.cart.entity.CartItem;
import java.util.List;

public record CheckoutResponseDTO(
        Long cartId,
        Long restaurantId,
        String restaurantName,
        int minimumOrderAmount,
        int deliveryFee,
        int subtotal,
        int totalAmount,
        int remainingAmount,
        boolean canOrder,
        List<CheckoutItemResponseDTO> items,
        List<String> availablePaymentMethods
) {

    public static CheckoutResponseDTO from(
            Cart cart,
            List<CartItem> cartItems,
            int subtotal,
            int deliveryFee,
            int totalAmount,
            List<String> availablePaymentMethods
    ) {
        int remainingAmount = Math.max(cart.getRestaurant().getMinimumOrderAmount() - subtotal, 0);

        return new CheckoutResponseDTO(
                cart.getCartId(),
                cart.getRestaurant().getRestaurantId(),
                cart.getRestaurant().getName(),
                cart.getRestaurant().getMinimumOrderAmount(),
                deliveryFee,
                subtotal,
                totalAmount,
                remainingAmount,
                subtotal >= cart.getRestaurant().getMinimumOrderAmount(),
                cartItems.stream()
                        .map(CheckoutItemResponseDTO::from)
                        .toList(),
                availablePaymentMethods
        );
    }
}
