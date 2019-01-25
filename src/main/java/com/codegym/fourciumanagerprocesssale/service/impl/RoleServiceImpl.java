package com.codegym.fourciumanagerprocesssale.service.impl;

import com.codegym.fourciumanagerprocesssale.model.Role;
import com.codegym.fourciumanagerprocesssale.repository.RoleRepository;
import com.codegym.fourciumanagerprocesssale.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }
}
