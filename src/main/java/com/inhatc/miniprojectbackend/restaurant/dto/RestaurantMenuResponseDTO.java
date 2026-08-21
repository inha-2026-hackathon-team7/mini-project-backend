package com.inhatc.miniprojectbackend.restaurant.dto;

import com.inhatc.miniprojectbackend.menu.entity.Menu;

public record RestaurantMenuResponseDTO(
        Long menuId,
        Long restaurantId,
        String name,
        String description,
        int price,
        String imageUrl,
        boolean available
) {

    public static RestaurantMenuResponseDTO from(Menu menu) {
        return new RestaurantMenuResponseDTO(
                menu.getMenuId(),
                menu.getRestaurant().getRestaurantId(),
                menu.getName(),
                menu.getDescription(),
                menu.getPrice(),
                menu.getImageUrl(),
                menu.isAvailable()
        );
    }
}
