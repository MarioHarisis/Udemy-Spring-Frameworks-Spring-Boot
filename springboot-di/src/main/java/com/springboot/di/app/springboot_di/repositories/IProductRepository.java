package com.springboot.di.app.springboot_di.repositories;

import java.util.List;

import com.springboot.di.app.springboot_di.controllers.Product;

public interface IProductRepository {
    List<Product> findAll();

    Product findById(Integer id);
}
