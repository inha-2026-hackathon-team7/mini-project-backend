package com.inhatc.miniprojectbackend.restaurant.dto;

import com.inhatc.miniprojectbackend.restaurant.entity.Restaurant;

public record RestaurantListResponseDTO(
        Long restaurantId,
        String name,
        String description,
        int minimumOrderAmount,
        int deliveryFee,
        boolean open,
        String thumbnailUrl
) {

    public static RestaurantListResponseDTO from(Restaurant restaurant, String thumbnailUrl) {
        return new RestaurantListResponseDTO(
                restaurant.getRestaurantId(),
                restaurant.getName(),
                restaurant.getDescription(),
                restaurant.getMinimumOrderAmount(),
                restaurant.getDeliveryFee(),
                restaurant.isOpen(),
                thumbnailUrl
        );
    }
}
