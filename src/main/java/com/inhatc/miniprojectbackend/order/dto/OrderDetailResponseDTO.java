package com.inhatc.miniprojectbackend.order.dto;

import com.inhatc.miniprojectbackend.order.entity.Order;
import com.inhatc.miniprojectbackend.order.entity.OrderItem;
import com.inhatc.miniprojectbackend.payment.dto.PaymentResponseDTO;
import com.inhatc.miniprojectbackend.payment.entity.Payment;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDetailResponseDTO(
        Long orderId,
        Long restaurantId,
        String restaurantName,
        String paymentType,
        String status,
        int requiredPayers,
        int totalAmount,
        LocalDateTime orderedAt,
        List<OrderItemResponseDTO> items,
        PaymentResponseDTO payment
) {

    public static OrderDetailResponseDTO from(
            Order order,
            List<OrderItem> orderItems,
            Payment payment
    ) {
        return new OrderDetailResponseDTO(
                order.getOrderId(),
                order.getRestaurant().getRestaurantId(),
                order.getRestaurant().getName(),
                order.getPaymentType().name(),
                order.getStatus().name(),
                order.getRequiredPayers(),
                order.getTotalAmount(),
                order.getOrderedAt(),
                orderItems.stream()
                        .map(OrderItemResponseDTO::from)
                        .toList(),
                PaymentResponseDTO.from(payment)
        );
    }
}
