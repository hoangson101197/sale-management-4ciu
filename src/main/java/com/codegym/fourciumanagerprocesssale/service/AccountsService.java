package com.codegym.fourciumanagerprocesssale.service;

import com.codegym.fourciumanagerprocesssale.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AccountsService {

    Page<User> findAll(Pageable pageable);

    Optional<User> findById(Long id);

    void remove(Long id);
}
