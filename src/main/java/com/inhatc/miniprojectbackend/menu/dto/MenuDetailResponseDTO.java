package com.inhatc.miniprojectbackend.menu.dto;

import com.inhatc.miniprojectbackend.menu.entity.Menu;

public record MenuDetailResponseDTO(
        Long menuId,
        Long restaurantId,
        String restaurantName,
        String name,
        String description,
        int price,
        String imageUrl,
        boolean available
) {

    public static MenuDetailResponseDTO from(Menu menu) {
        return new MenuDetailResponseDTO(
                menu.getMenuId(),
                menu.getRestaurant().getRestaurantId(),
                menu.getRestaurant().getName(),
                menu.getName(),
                menu.getDescription(),
                menu.getPrice(),
                menu.getImageUrl(),
                menu.isAvailable()
        );
    }
}
