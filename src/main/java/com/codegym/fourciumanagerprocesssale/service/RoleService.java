package com.codegym.fourciumanagerprocesssale.service;

import com.codegym.fourciumanagerprocesssale.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RoleService {
    Page<Role> findAll(Pageable pageable);

    Optional<Role> findById(Long id);
}
