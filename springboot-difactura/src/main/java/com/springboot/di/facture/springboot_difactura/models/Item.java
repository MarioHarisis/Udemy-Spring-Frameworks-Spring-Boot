package com.springboot.di.facture.springboot_difactura.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {

    private Product product;
    private Integer quantity;

    public Item() {
    }

    public Item(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // No tenemos ningún atributo 'subtotal' pero al usar un método get, se
    // transforma automáticamente a JSON y lo podemos ver
    public int getSubtotal() {
        return quantity * product.getPrice();
    }
}
