package com.codegym.fourciumanagerprocesssale.repository;

import com.codegym.fourciumanagerprocesssale.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductsRepository extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findAllByNameContaining(String s, Pageable pageable);
}
