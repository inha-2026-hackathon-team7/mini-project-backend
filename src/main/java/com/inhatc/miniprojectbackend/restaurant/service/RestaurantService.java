package com.inhatc.miniprojectbackend.restaurant.service;

import com.inhatc.miniprojectbackend.restaurant.dto.RestaurantListResponseDTO;
import com.inhatc.miniprojectbackend.restaurant.entity.Restaurant;
import com.inhatc.miniprojectbackend.restaurant.entity.RestaurantImage;
import com.inhatc.miniprojectbackend.restaurant.entity.RestaurantImageType;
import com.inhatc.miniprojectbackend.restaurant.repository.RestaurantImageRepository;
import com.inhatc.miniprojectbackend.restaurant.repository.RestaurantRepository;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantImageRepository restaurantImageRepository;

    // 음식점 목록 가져오기
    public List<RestaurantListResponseDTO> getRestaurantList() {
        List<Restaurant> restaurants = restaurantRepository.findAllByOrderByRestaurantIdAsc();

        List<Long> restaurantIds = restaurants.stream()
                .map(Restaurant::getRestaurantId)
                .toList();
        Map<Long, RestaurantImage> thumbnailByRestaurantId = restaurantImageRepository
                .findAllByTypeAndRestaurantRestaurantIdIn(RestaurantImageType.thumbnail, restaurantIds)
                .stream()
                .collect(Collectors.toMap(
                        image -> image.getRestaurant().getRestaurantId(),
                        Function.identity(),
                        (first, ignored) -> first
                ));

        return restaurants.stream()
                .map(restaurant -> RestaurantListResponseDTO.from(
                        restaurant,
                        getThumbnailUrl(thumbnailByRestaurantId, restaurant.getRestaurantId())
                ))
                .toList();
    }

    private String getThumbnailUrl(Map<Long, RestaurantImage> thumbnailByRestaurantId, Long restaurantId) {
        RestaurantImage thumbnail = thumbnailByRestaurantId.get(restaurantId);
        return thumbnail == null ? null : thumbnail.getImageUrl();
    }
}
