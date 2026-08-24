package com.inhatc.miniprojectbackend.order.repository;

import com.inhatc.miniprojectbackend.order.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"restaurant"})
    List<Order> findAllBySessionIdOrderByOrderIdDesc(String sessionId);
}
