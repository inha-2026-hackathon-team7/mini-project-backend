package com.inhatc.miniprojectbackend.cart.entity;

import com.inhatc.miniprojectbackend.menu.entity.Menu;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "cart_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long cartItemId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(nullable = false)
    private int quantity;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    private CartItem(Cart cart, Menu menu, int quantity) {
        this.cart = cart;
        this.menu = menu;
        this.quantity = quantity;
    }

    public static CartItem create(Cart cart, Menu menu, int quantity) {
        return new CartItem(cart, menu, quantity);
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void changeQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean belongsTo(Cart cart) {
        return this.cart.getCartId().equals(cart.getCartId());
    }
}
