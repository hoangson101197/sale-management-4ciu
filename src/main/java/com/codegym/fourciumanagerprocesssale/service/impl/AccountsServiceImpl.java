package com.codegym.fourciumanagerprocesssale.service.impl;

import com.codegym.fourciumanagerprocesssale.model.User;
import com.codegym.fourciumanagerprocesssale.repository.UserRepository;
import com.codegym.fourciumanagerprocesssale.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountsServiceImpl implements AccountsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }
}
