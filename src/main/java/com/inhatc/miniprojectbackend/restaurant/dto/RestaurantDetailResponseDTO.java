package com.inhatc.miniprojectbackend.restaurant.dto;

import com.inhatc.miniprojectbackend.menu.entity.Menu;
import com.inhatc.miniprojectbackend.restaurant.entity.Restaurant;
import com.inhatc.miniprojectbackend.restaurant.entity.RestaurantImage;
import java.util.List;

public record RestaurantDetailResponseDTO(
        Long restaurantId,
        String name,
        String description,
        int minimumOrderAmount,
        int deliveryFee,
        boolean open,
        List<RestaurantImageResponseDTO> images,
        List<RestaurantMenuResponseDTO> menus
) {

    public static RestaurantDetailResponseDTO from(
            Restaurant restaurant,
            List<RestaurantImage> images,
            List<Menu> menus
    ) {
        return new RestaurantDetailResponseDTO(
                restaurant.getRestaurantId(),
                restaurant.getName(),
                restaurant.getDescription(),
                restaurant.getMinimumOrderAmount(),
                restaurant.getDeliveryFee(),
                restaurant.isOpen(),
                images.stream()
                        .map(RestaurantImageResponseDTO::from)
                        .toList(),
                menus.stream()
                        .map(RestaurantMenuResponseDTO::from)
                        .toList()
        );
    }
}
