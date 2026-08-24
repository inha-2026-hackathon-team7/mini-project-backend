package com.inhatc.miniprojectbackend.order.controller;

import com.inhatc.miniprojectbackend.global.session.SessionCookieManager;
import com.inhatc.miniprojectbackend.order.dto.OrderCreateRequestDTO;
import com.inhatc.miniprojectbackend.order.dto.OrderCreateResponseDTO;
import com.inhatc.miniprojectbackend.order.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final SessionCookieManager sessionCookieManager;

    // 주문 생성
    @PostMapping
    public ResponseEntity<OrderCreateResponseDTO> createOrder(
            @RequestBody OrderCreateRequestDTO orderCreateRequestDTO,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        String sessionId = sessionCookieManager.getOrCreateSessionId(request, response);
        return ResponseEntity.ok(orderService.createOrder(sessionId, orderCreateRequestDTO));
    }
}
