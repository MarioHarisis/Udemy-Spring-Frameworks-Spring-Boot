package com.springboot.di.app.springboot_di.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.di.app.springboot_di.services.IProductService;

@RestController
@RequestMapping("/api")
public class SomeController {

    // private ProductServiceImpl productService = new ProductServiceImpl();
    @Autowired
    private IProductService productService; // inyección de dependencia mediante interfaz

    @GetMapping
    public List<Product> list() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product show(@PathVariable Integer id) {
        return productService.findById(id);
    }
}
