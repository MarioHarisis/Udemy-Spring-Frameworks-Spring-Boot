package com.curso.springboot.springboot_web.models.dto;

import com.curso.springboot.springboot_web.models.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

/*
 * UserDto permite que controles qué datos quieres exponer. Por ejemplo, si la
 * entidad User
 * tiene datos sensibles como una contraseña o un número de identificación que
 * no quieres exponer
 * a través de la API, puedes omitir esos campos en el DTO
 */
public class UserDto {

    private String title;
    private User user;

}
