package com.codegym.fourciumanagerprocesssale.service;

import com.codegym.fourciumanagerprocesssale.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductsService {

    Page<Product> findAll(Pageable pageable);

    void remove(Long id);

    void save(Product product);
}
