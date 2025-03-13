package com.springboot.di.app.springboot_di.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.di.app.springboot_di.controllers.Product;

public class ProductRepositoryJson implements IProductRepository {

    private List<Product> list;

    // constructor por defecto
    public ProductRepositoryJson() {
        /* leer el archivo product.json */

        // obtener el path del archivo
        ClassPathResource resource = new ClassPathResource("json/product.json");

        // clase de la biblioteca Jackson que se utiliza para convertir entre objetos
        // Java y JSON
        ObjectMapper mapper = new ObjectMapper();
        try {
            /*
             * mapper.readValue(): Se utiliza para deserializar el contenido del archivo
             * JSON
             * resource.getFile() obtiene el archivo del recurso como un objeto File
             * Product[].class: Indica que el JSON debe ser deserializado en un arreglo de
             * objetos Product
             */
            list = Arrays.asList(mapper.readValue(resource.getFile(), Product[].class));
        } catch (IOException e) {
            e.getMessage();
        }
    }

    @Override
    public List<Product> findAll() {
        return list;
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
        return list.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow();
    }

}
