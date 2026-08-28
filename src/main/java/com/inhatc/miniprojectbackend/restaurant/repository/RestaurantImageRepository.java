package com.inhatc.miniprojectbackend.restaurant.repository;

import com.inhatc.miniprojectbackend.restaurant.entity.RestaurantImage;
import com.inhatc.miniprojectbackend.restaurant.entity.RestaurantImageType;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantImageRepository extends JpaRepository<RestaurantImage, Long> {

    List<RestaurantImage> findAllByTypeAndRestaurantRestaurantIdIn(
            RestaurantImageType type,
            Collection<Long> restaurantIds
    );

    List<RestaurantImage> findAllByRestaurantRestaurantIdOrderBySortOrderAsc(Long restaurantId);
}
