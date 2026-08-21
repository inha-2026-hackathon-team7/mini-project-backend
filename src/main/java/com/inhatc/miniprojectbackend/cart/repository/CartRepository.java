package com.inhatc.miniprojectbackend.cart.repository;

import com.inhatc.miniprojectbackend.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
