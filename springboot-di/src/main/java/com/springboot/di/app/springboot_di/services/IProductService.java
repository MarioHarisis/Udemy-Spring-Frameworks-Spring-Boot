package com.springboot.di.app.springboot_di.services;

import java.util.List;

import com.springboot.di.app.springboot_di.controllers.Product;

public interface IProductService {
    List<Product> findAll();

    Product findById(Integer id);

}
