package com.inhatc.miniprojectbackend.cart.repository;

import com.inhatc.miniprojectbackend.cart.entity.CartItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @EntityGraph(attributePaths = {"menu"})
    List<CartItem> findAllByCartCartIdOrderByCartItemIdAsc(Long cartId);

    Optional<CartItem> findByCartCartIdAndMenuMenuId(Long cartId, Long menuId);

    boolean existsByCartCartId(Long cartId);
}
