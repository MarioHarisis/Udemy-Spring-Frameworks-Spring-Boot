package com.springboot.jpa.springboot_jpa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDto {
    /*
     * Las clases DTO (Data Transfer Objects) en Java son un patrón de diseño
     * DTOs permiten optimizar qué datos se envían o reciben. Puedes crear un DTO
     * que incluya solo los campos necesarios para una operación particular,
     * evitando la sobrecarga de enviar datos innecesarios.
     */

    private String name;
    private String lastname;

    public PersonDto(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "PersonDto [name=" + name + ", lastname=" + lastname + "]";
    }

}
