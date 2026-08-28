package com.inhatc.miniprojectbackend.menu.controller;

import com.inhatc.miniprojectbackend.menu.dto.MenuDetailResponseDTO;
import com.inhatc.miniprojectbackend.menu.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Menu", description = "메뉴 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    @Operation(summary = "메뉴 상세 조회", description = "메뉴 ID로 메뉴 상세 정보를 조회합니다.")
    @GetMapping("/{menuId}")
    public ResponseEntity<MenuDetailResponseDTO> getMenuDetail(
            @Parameter(description = "메뉴 ID", example = "1") @PathVariable Long menuId
    ) {
        return ResponseEntity.ok(menuService.getMenuDetail(menuId));
    }
}
