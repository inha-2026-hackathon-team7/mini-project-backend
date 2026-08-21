package com.inhatc.miniprojectbackend.order.repository;

import com.inhatc.miniprojectbackend.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
