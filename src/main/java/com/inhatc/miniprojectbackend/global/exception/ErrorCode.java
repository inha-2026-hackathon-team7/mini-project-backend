package com.inhatc.miniprojectbackend.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    RESTAURANT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 음식점을 찾을 수 없습니다."),
    MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 메뉴를 찾을 수 없습니다."),
    CART_NOT_FOUND(HttpStatus.NOT_FOUND, "장바구니를 찾을 수 없습니다."),
    CART_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "장바구니 메뉴를 찾을 수 없습니다."),
    CART_RESTAURANT_MISMATCH(HttpStatus.CONFLICT, "다른 음식점의 메뉴가 장바구니에 담겨 있습니다."),
    EMPTY_CART(HttpStatus.BAD_REQUEST, "장바구니가 비어 있습니다."),
    UNAVAILABLE_MENU(HttpStatus.BAD_REQUEST, "판매 중이 아닌 메뉴가 포함되어 있습니다."),
    MINIMUM_ORDER_AMOUNT_NOT_MET(HttpStatus.BAD_REQUEST, "최소 주문금액을 충족하지 못했습니다."),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "주문 정보를 찾을 수 없습니다."),
    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "결제 정보를 찾을 수 없습니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
