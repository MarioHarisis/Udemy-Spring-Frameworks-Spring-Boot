package com.springboot.di.app.springboot_di.controllers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product implements Cloneable {

    private Integer id;
    private String name;
    private Double price;

    public Product() {
    }

    public Product(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // clonar un producto
    @Override
    public Object clone() {

        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // si no consigue clonarlo, lo creará manualmente
            return new Product(this.getId(), this.getName(), this.getPrice());
            /*
             * Usar métodos getter y setter en lugar de acceder directamente a los atributos
             * ayuda a mantener el principio de encapsulamiento. Esto significa que puedes
             * cambiar la implementación del método getId() sin afectar a otras partes del
             * código que lo utilizan.
             */
        }
    }

}
