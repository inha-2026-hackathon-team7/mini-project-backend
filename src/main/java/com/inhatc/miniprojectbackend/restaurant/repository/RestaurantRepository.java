package com.inhatc.miniprojectbackend.restaurant.repository;

import com.inhatc.miniprojectbackend.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
