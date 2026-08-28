package com.inhatc.miniprojectbackend.order.entity;

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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "order_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long orderItemId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(name = "menu_name", nullable = false, length = 100)
    private String menuName;

    @Column(name = "menu_price", nullable = false)
    private int menuPrice;

    @Column(nullable = false)
    private int quantity;

    private OrderItem(Order order, Menu menu, int quantity) {
        this.order = order;
        this.menu = menu;
        this.menuName = menu.getName();
        this.menuPrice = menu.getPrice();
        this.quantity = quantity;
    }

    public static OrderItem create(Order order, Menu menu, int quantity) {
        return new OrderItem(order, menu, quantity);
    }
}
