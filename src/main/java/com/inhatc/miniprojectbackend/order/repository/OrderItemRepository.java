package com.inhatc.miniprojectbackend.order.repository;

import com.inhatc.miniprojectbackend.order.entity.OrderItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findAllByOrderOrderIdOrderByOrderItemIdAsc(Long orderId);
}
