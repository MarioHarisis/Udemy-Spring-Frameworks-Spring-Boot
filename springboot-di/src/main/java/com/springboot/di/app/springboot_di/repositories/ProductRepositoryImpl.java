package com.springboot.di.app.springboot_di.repositories;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springboot.di.app.springboot_di.controllers.Product;

/* Aquí está la conexión con la DB */

// @RequestScope: maneja el ciclo de vida, en este caso se reinicia por cada request
// @SessionScope: maneja el ciclo de vida, en este caso se reinicia por cada sesión
@Repository("productList")
public class ProductRepositoryImpl implements IProductRepository {

    // Simulación de DB
    private List<Product> data;

    public ProductRepositoryImpl() {
        // Los productos que estarían en la DB
        this.data = Arrays.asList(
                new Product(1, "Teclado", 60.0),
                new Product(2, "Raton", 15.30),
                new Product(3, "Monitor", 120.0),
                new Product(4, "Altavoces", 25.20));
    }

    // obtener la lista data completa
    @Override
    public List<Product> findAll() {
        return data;
    }

    @Override
    public Product findById(Integer id) {
        /*
         * stream(): Convierte la colección data (que podría ser una lista o conjunto de
         * productos) en un stream para permitir operaciones funcionales, como filtrado
         * o búsqueda.
         * usa una expresión lambda (p -> p.getId().equals(id)) para comparar el id de
         * cada producto con el id que se pasó como argumento al método.
         * orElseThrow() significa que, si el resultado de findFirst() es vacío (no
         * encontró ningún producto), lanzará una excepción
         */
        return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow();
    }
}
