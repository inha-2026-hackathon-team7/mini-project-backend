package com.inhatc.miniprojectbackend.restaurant.dto;

import com.inhatc.miniprojectbackend.restaurant.entity.RestaurantImage;

public record RestaurantImageResponseDTO(
        Long imageId,
        String imageUrl,
        String type,
        int sortOrder
) {

    public static RestaurantImageResponseDTO from(RestaurantImage image) {
        return new RestaurantImageResponseDTO(
                image.getImageId(),
                image.getImageUrl(),
                image.getType().name(),
                image.getSortOrder()
        );
    }
}
