package com.inhatc.miniprojectbackend.menu.controller;

import com.inhatc.miniprojectbackend.menu.dto.MenuDetailResponseDTO;
import com.inhatc.miniprojectbackend.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    // 메뉴 상세
    @GetMapping("/{menuId}")
    public ResponseEntity<MenuDetailResponseDTO> getMenuDetail(
            @PathVariable Long menuId
    ) {
        return ResponseEntity.ok(menuService.getMenuDetail(menuId));
    }
}
