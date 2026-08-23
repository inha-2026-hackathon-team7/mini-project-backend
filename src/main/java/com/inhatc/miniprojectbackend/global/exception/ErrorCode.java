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
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
