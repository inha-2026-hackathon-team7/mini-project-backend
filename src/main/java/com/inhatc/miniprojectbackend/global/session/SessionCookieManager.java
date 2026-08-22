package com.inhatc.miniprojectbackend.global.session;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.UUID;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class SessionCookieManager {

    public static final String SESSION_COOKIE_NAME = "FOOD_ORDER_SESSION";
    private static final Duration COOKIE_MAX_AGE = Duration.ofDays(7);

    public String getOrCreateSessionId(HttpServletRequest request, HttpServletResponse response) {
        String sessionId = findSessionId(request);

        if (sessionId != null) {
            return sessionId;
        }

        String newSessionId = UUID.randomUUID().toString();
        addSessionCookie(response, newSessionId);

        return newSessionId;
    }

    private String findSessionId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return null;
        }

        return Arrays.stream(cookies)
                .filter(cookie -> SESSION_COOKIE_NAME.equals(cookie.getName()))
                .map(Cookie::getValue)
                .filter(value -> value != null && !value.isBlank())
                .findFirst()
                .orElse(null);
    }

    private void addSessionCookie(HttpServletResponse response, String sessionId) {
        ResponseCookie cookie = ResponseCookie.from(SESSION_COOKIE_NAME, sessionId)
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/")
                .maxAge(COOKIE_MAX_AGE)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
