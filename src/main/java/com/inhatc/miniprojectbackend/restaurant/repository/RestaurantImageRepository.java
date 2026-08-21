package com.inhatc.miniprojectbackend.restaurant.repository;

import com.inhatc.miniprojectbackend.restaurant.entity.RestaurantImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantImageRepository extends JpaRepository<RestaurantImage, Long> {
}
