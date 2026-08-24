package com.inhatc.miniprojectbackend.order.entity;

import com.inhatc.miniprojectbackend.restaurant.entity.Restaurant;
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
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "session_id", nullable = false, length = 36)
    private String sessionId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false, columnDefinition = "ENUM('single', 'split')")
    private OrderPaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('pending', 'paid', 'cooking', 'delivering', 'completed', 'cancelled')")
    private OrderStatus status = OrderStatus.pending;

    @Column(name = "required_payers", nullable = false)
    private int requiredPayers = 1;

    @Column(name = "total_amount", nullable = false)
    private int totalAmount;

    @CreationTimestamp
    @Column(name = "ordered_at", nullable = false, updatable = false)
    private LocalDateTime orderedAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    private Order(
            String sessionId,
            Restaurant restaurant,
            OrderPaymentType paymentType,
            OrderStatus status,
            int requiredPayers,
            int totalAmount
    ) {
        this.sessionId = sessionId;
        this.restaurant = restaurant;
        this.paymentType = paymentType;
        this.status = status;
        this.requiredPayers = requiredPayers;
        this.totalAmount = totalAmount;
    }

    public static Order create(
            String sessionId,
            Restaurant restaurant,
            OrderPaymentType paymentType,
            OrderStatus status,
            int requiredPayers,
            int totalAmount
    ) {
        return new Order(sessionId, restaurant, paymentType, status, requiredPayers, totalAmount);
    }
}
