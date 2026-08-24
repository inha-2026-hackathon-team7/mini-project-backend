package com.inhatc.miniprojectbackend.payment.dto;

import com.inhatc.miniprojectbackend.payment.entity.Payment;

public record PaymentResponseDTO(
        Long paymentId,
        String paymentMethod,
        int paidAmount,
        String status
) {

    public static PaymentResponseDTO from(Payment payment) {
        return new PaymentResponseDTO(
                payment.getPaymentId(),
                payment.getPaymentMethod().name(),
                payment.getPaidAmount(),
                payment.getStatus().name()
        );
    }
}
