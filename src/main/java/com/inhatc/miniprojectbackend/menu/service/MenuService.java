package com.inhatc.miniprojectbackend.menu.service;

import com.inhatc.miniprojectbackend.global.exception.BusinessException;
import com.inhatc.miniprojectbackend.global.exception.ErrorCode;
import com.inhatc.miniprojectbackend.menu.dto.MenuDetailResponseDTO;
import com.inhatc.miniprojectbackend.menu.entity.Menu;
import com.inhatc.miniprojectbackend.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final MenuRepository menuRepository;

    // 메뉴 상세 정보 가져오기
    public MenuDetailResponseDTO getMenuDetail(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MENU_NOT_FOUND));

        return MenuDetailResponseDTO.from(menu);
    }
}
