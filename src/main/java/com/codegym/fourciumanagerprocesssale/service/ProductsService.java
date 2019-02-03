package com.codegym.fourciumanagerprocesssale.service;

import com.codegym.fourciumanagerprocesssale.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductsService {

    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllByNameContaining(String firstname, Pageable pageable);

    Optional<Product> findById(Long id);

    void remove(Long id);

    void save(Product product);
}
