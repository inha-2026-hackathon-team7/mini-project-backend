package com.inhatc.miniprojectbackend.restaurant.controller;

import com.inhatc.miniprojectbackend.restaurant.dto.RestaurantDetailResponseDTO;
import com.inhatc.miniprojectbackend.restaurant.dto.RestaurantListResponseDTO;
import com.inhatc.miniprojectbackend.restaurant.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Restaurant", description = "음식점 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Operation(summary = "음식점 목록 조회", description = "등록된 모든 음식점 목록을 조회합니다.")
    @GetMapping("/list")
    public ResponseEntity<List<RestaurantListResponseDTO>> getRestaurantList() {
        return ResponseEntity.ok(restaurantService.getRestaurantList());
    }

    @Operation(summary = "음식점 상세 조회", description = "음식점 ID로 음식점 상세 정보를 조회합니다.")
    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDetailResponseDTO> getRestaurantDetail(
            @Parameter(description = "음식점 ID", example = "1") @PathVariable Long restaurantId
    ) {
        return ResponseEntity.ok(restaurantService.getRestaurantDetail(restaurantId));
    }
}
