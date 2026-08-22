package com.inhatc.miniprojectbackend.cart.repository;

import com.inhatc.miniprojectbackend.cart.entity.Cart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @EntityGraph(attributePaths = {"restaurant"})
    Optional<Cart> findBySessionId(String sessionId);
}
