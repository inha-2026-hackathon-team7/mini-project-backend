package com.inhatc.miniprojectbackend.global.exception;

public record ErrorResponseDTO(
        int status,
        String code,
        String message,
        String path
) {

    public static ErrorResponseDTO of(ErrorCode errorCode, String path) {
        return new ErrorResponseDTO(
                errorCode.getStatus().value(),
                errorCode.name(),
                errorCode.getMessage(),
                path
        );
    }
}
