package com.springboot.di.facture.springboot_difactura;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.List;
import com.springboot.di.facture.springboot_difactura.models.Item;
import com.springboot.di.facture.springboot_difactura.models.Product;

@Configuration
@PropertySource(value = "classpath:data.properties", encoding = "UTF-8")
public class AppConfig {

    // @Primary para determinar que Bean es el que tiene pioridad
    @Bean("default")
    List<Item> itemsInvoice() {
        Product p1 = new Product("Camara Sony", 500);
        Product p2 = new Product("Bicicleta Bianchi 26", 1200);
        return Arrays.asList(new Item(p1, 2), new Item(p2, 4));
    }

    @Bean
    List<Item> itemsInvoiceOffice() {
        Product p1 = new Product("Asus 24' ", 600);
        Product p2 = new Product("Macbook", 1500);
        Product p3 = new Product("Reloj", 7000);
        return Arrays.asList(new Item(p1, 2), new Item(p2, 4), new Item(p3, 1));
    }
}
