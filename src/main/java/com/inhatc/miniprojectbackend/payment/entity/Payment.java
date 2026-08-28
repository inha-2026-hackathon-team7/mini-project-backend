package com.inhatc.miniprojectbackend.payment.entity;

import com.inhatc.miniprojectbackend.order.entity.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Entity
@Table(name = "payments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "session_id", nullable = false, length = 36)
    private String sessionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false, columnDefinition = "ENUM('card', 'transfer', 'cash')")
    private PaymentMethod paymentMethod;

    @Column(name = "paid_amount", nullable = false)
    private int paidAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('pending', 'paid', 'failed')")
    private PaymentStatus status = PaymentStatus.pending;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    private Payment(
            Order order,
            String sessionId,
            PaymentMethod paymentMethod,
            int paidAmount,
            PaymentStatus status
    ) {
        this.order = order;
        this.sessionId = sessionId;
        this.paymentMethod = paymentMethod;
        this.paidAmount = paidAmount;
        this.status = status;
    }

    public static Payment create(
            Order order,
            String sessionId,
            PaymentMethod paymentMethod,
            int paidAmount,
            PaymentStatus status
    ) {
        return new Payment(order, sessionId, paymentMethod, paidAmount, status);
    }
}
