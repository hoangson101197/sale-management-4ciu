package com.codegym.fourciumanagerprocesssale.repository;

import com.codegym.fourciumanagerprocesssale.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
    Role findByRole(String role);
}
