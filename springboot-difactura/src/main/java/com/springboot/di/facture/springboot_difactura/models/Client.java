package com.springboot.di.facture.springboot_difactura.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class Client {

    @Value("${client.name}") // Inyectamos los valores mediante un properties
    private String name;

    @Value("${client.lastname}") // Inyectamos los valores mediante un properties
    private String lastname;

}