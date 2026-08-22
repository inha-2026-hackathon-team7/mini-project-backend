package com.inhatc.miniprojectbackend.cart.dto;

public record CartItemCreateRequestDTO(
        Long menuId,
        int quantity,
        boolean clearExisting
) {
}
