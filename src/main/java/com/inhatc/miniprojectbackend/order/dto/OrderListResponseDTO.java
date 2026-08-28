package com.inhatc.miniprojectbackend.order.dto;

import com.inhatc.miniprojectbackend.order.entity.Order;
import java.time.LocalDateTime;

public record OrderListResponseDTO(
        Long orderId,
        Long restaurantId,
        String restaurantName,
        String status,
        int totalAmount,
        LocalDateTime orderedAt
) {

    public static OrderListResponseDTO from(Order order) {
        return new OrderListResponseDTO(
                order.getOrderId(),
                order.getRestaurant().getRestaurantId(),
                order.getRestaurant().getName(),
                order.getStatus().name(),
                order.getTotalAmount(),
                order.getOrderedAt()
        );
    }
}
