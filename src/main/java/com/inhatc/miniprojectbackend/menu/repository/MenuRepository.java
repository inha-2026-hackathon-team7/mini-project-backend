package com.inhatc.miniprojectbackend.menu.repository;

import com.inhatc.miniprojectbackend.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
