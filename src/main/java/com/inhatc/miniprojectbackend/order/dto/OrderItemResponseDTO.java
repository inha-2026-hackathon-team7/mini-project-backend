package com.inhatc.miniprojectbackend.order.dto;

import com.inhatc.miniprojectbackend.order.entity.OrderItem;

public record OrderItemResponseDTO(
        Long orderItemId,
        Long menuId,
        String menuName,
        int menuPrice,
        int quantity,
        int itemTotalAmount
) {

    public static OrderItemResponseDTO from(OrderItem orderItem) {
        return new OrderItemResponseDTO(
                orderItem.getOrderItemId(),
                orderItem.getMenu().getMenuId(),
                orderItem.getMenuName(),
                orderItem.getMenuPrice(),
                orderItem.getQuantity(),
                orderItem.getMenuPrice() * orderItem.getQuantity()
        );
    }
}
