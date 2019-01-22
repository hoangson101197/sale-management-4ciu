package com.codegym.fourciumanagerprocesssale.repository;

import com.codegym.fourciumanagerprocesssale.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByEmail(String email);
}
