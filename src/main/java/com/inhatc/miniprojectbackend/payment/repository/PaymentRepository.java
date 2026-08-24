package com.inhatc.miniprojectbackend.payment.repository;

import com.inhatc.miniprojectbackend.payment.entity.Payment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByOrderOrderId(Long orderId);
}
