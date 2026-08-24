package com.inhatc.miniprojectbackend.order.service;

import com.inhatc.miniprojectbackend.cart.entity.Cart;
import com.inhatc.miniprojectbackend.cart.entity.CartItem;
import com.inhatc.miniprojectbackend.cart.repository.CartItemRepository;
import com.inhatc.miniprojectbackend.cart.repository.CartRepository;
import com.inhatc.miniprojectbackend.global.exception.BusinessException;
import com.inhatc.miniprojectbackend.global.exception.ErrorCode;
import com.inhatc.miniprojectbackend.order.dto.CheckoutResponseDTO;
import com.inhatc.miniprojectbackend.order.dto.OrderCreateRequestDTO;
import com.inhatc.miniprojectbackend.order.dto.OrderCreateResponseDTO;
import com.inhatc.miniprojectbackend.order.entity.Order;
import com.inhatc.miniprojectbackend.order.entity.OrderItem;
import com.inhatc.miniprojectbackend.order.entity.OrderPaymentType;
import com.inhatc.miniprojectbackend.order.entity.OrderStatus;
import com.inhatc.miniprojectbackend.order.repository.OrderItemRepository;
import com.inhatc.miniprojectbackend.order.repository.OrderRepository;
import com.inhatc.miniprojectbackend.payment.entity.Payment;
import com.inhatc.miniprojectbackend.payment.entity.PaymentMethod;
import com.inhatc.miniprojectbackend.payment.entity.PaymentStatus;
import com.inhatc.miniprojectbackend.payment.repository.PaymentRepository;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final PaymentRepository paymentRepository;

    // 결제 화면 정보 조회
    public CheckoutResponseDTO getCheckout(String sessionId) {
        Cart cart = getCartOrThrow(sessionId);
        List<CartItem> cartItems = getCartItemsOrThrow(cart);
        validateOrderableCart(cart, cartItems);

        OrderAmount orderAmount = calculateOrderAmount(cart, cartItems);

        return CheckoutResponseDTO.from(
                cart,
                cartItems,
                orderAmount.subtotal(),
                orderAmount.deliveryFee(),
                orderAmount.totalAmount(),
                getAvailablePaymentMethods()
        );
    }

    // 주문 생성
    @Transactional
    public OrderCreateResponseDTO createOrder(String sessionId, OrderCreateRequestDTO request) {
        validateOrderCreateRequest(request);

        Cart cart = getCartOrThrow(sessionId);
        List<CartItem> cartItems = getCartItemsOrThrow(cart);
        validateOrderableCart(cart, cartItems);

        OrderAmount orderAmount = calculateOrderAmount(cart, cartItems);
        Order order = saveOrder(sessionId, cart, request, orderAmount.totalAmount());
        List<OrderItem> orderItems = saveOrderItems(order, cartItems);
        Payment payment = savePayment(order, sessionId, request, orderAmount.totalAmount());

        clearCart(cart);

        return OrderCreateResponseDTO.from(order, orderItems, payment);
    }

    private List<String> getAvailablePaymentMethods() {
        return Arrays.stream(PaymentMethod.values())
                .map(PaymentMethod::name)
                .toList();
    }

    private void validateOrderCreateRequest(OrderCreateRequestDTO request) {
        if (request.paymentType() == null
                || request.paymentMethod() == null
                || request.requiredPayers() != 1
                || request.paymentType() != OrderPaymentType.single) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST);
        }
    }

    private Cart getCartOrThrow(String sessionId) {
        return cartRepository.findBySessionId(sessionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.CART_NOT_FOUND));
    }

    private List<CartItem> getCartItemsOrThrow(Cart cart) {
        List<CartItem> cartItems = cartItemRepository
                .findAllByCartCartIdOrderByCartItemIdAsc(cart.getCartId());

        if (cartItems.isEmpty()) {
            throw new BusinessException(ErrorCode.EMPTY_CART);
        }

        return cartItems;
    }

    private void validateOrderableCart(Cart cart, List<CartItem> cartItems) {
        validateAvailableMenus(cartItems);

        OrderAmount orderAmount = calculateOrderAmount(cart, cartItems);
        if (orderAmount.subtotal() < cart.getRestaurant().getMinimumOrderAmount()) {
            throw new BusinessException(ErrorCode.MINIMUM_ORDER_AMOUNT_NOT_MET);
        }
    }

    private void validateAvailableMenus(List<CartItem> cartItems) {
        boolean hasUnavailableMenu = cartItems.stream()
                .anyMatch(cartItem -> !cartItem.getMenu().isAvailable());

        if (hasUnavailableMenu) {
            throw new BusinessException(ErrorCode.UNAVAILABLE_MENU);
        }
    }

    private OrderAmount calculateOrderAmount(Cart cart, List<CartItem> cartItems) {
        int subtotal = cartItems.stream()
                .mapToInt(cartItem -> cartItem.getMenu().getPrice() * cartItem.getQuantity())
                .sum();
        int deliveryFee = cart.getRestaurant().getDeliveryFee();

        return new OrderAmount(subtotal, deliveryFee, subtotal + deliveryFee);
    }

    private Order saveOrder(
            String sessionId,
            Cart cart,
            OrderCreateRequestDTO request,
            int totalAmount
    ) {
        return orderRepository.save(Order.create(
                sessionId,
                cart.getRestaurant(),
                request.paymentType(),
                OrderStatus.paid,
                request.requiredPayers(),
                totalAmount
        ));
    }

    private List<OrderItem> saveOrderItems(Order order, List<CartItem> cartItems) {
        List<OrderItem> orderItems = cartItems.stream()
                .map(cartItem -> OrderItem.create(order, cartItem.getMenu(), cartItem.getQuantity()))
                .toList();

        return orderItemRepository.saveAll(orderItems);
    }

    private Payment savePayment(
            Order order,
            String sessionId,
            OrderCreateRequestDTO request,
            int totalAmount
    ) {
        return paymentRepository.save(Payment.create(
                order,
                sessionId,
                request.paymentMethod(),
                totalAmount,
                PaymentStatus.paid
        ));
    }

    private void clearCart(Cart cart) {
        cartRepository.delete(cart);
    }

    private record OrderAmount(
            int subtotal,
            int deliveryFee,
            int totalAmount
    ) {
    }
}
