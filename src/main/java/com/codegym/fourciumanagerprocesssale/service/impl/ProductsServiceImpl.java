package com.codegym.fourciumanagerprocesssale.service.impl;

import com.codegym.fourciumanagerprocesssale.model.Product;
import com.codegym.fourciumanagerprocesssale.repository.ProductsRepository;
import com.codegym.fourciumanagerprocesssale.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productsRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findAllByNameContaining(String firstname, Pageable pageable) {
        return productsRepository.findAllByNameContaining(firstname, pageable);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productsRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        productsRepository.deleteById(id);
    }

    @Override
    public void save(Product product) {
        productsRepository.save(product);
    }
}
