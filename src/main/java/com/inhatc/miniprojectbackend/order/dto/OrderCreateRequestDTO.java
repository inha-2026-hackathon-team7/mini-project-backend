package com.inhatc.miniprojectbackend.order.dto;

import com.inhatc.miniprojectbackend.order.entity.OrderPaymentType;
import com.inhatc.miniprojectbackend.payment.entity.PaymentMethod;

public record OrderCreateRequestDTO(
        OrderPaymentType paymentType,
        int requiredPayers,
        PaymentMethod paymentMethod
) {
}
