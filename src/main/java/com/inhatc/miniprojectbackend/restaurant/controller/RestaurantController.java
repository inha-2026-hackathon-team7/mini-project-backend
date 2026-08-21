package com.inhatc.miniprojectbackend.restaurant.controller;

import com.inhatc.miniprojectbackend.restaurant.dto.RestaurantDetailResponseDTO;
import com.inhatc.miniprojectbackend.restaurant.dto.RestaurantListResponseDTO;
import com.inhatc.miniprojectbackend.restaurant.service.RestaurantService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    // 음식점 목록
    @GetMapping("/list")
    public ResponseEntity<List<RestaurantListResponseDTO>> getRestaurantList() {
        return ResponseEntity.ok(restaurantService.getRestaurantList());
    }

    // 음식점 상세
    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDetailResponseDTO> getRestaurantDetail(
            @PathVariable Long restaurantId
    ) {
        return ResponseEntity.ok(restaurantService.getRestaurantDetail(restaurantId));
    }
}
