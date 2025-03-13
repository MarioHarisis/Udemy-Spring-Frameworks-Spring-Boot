package com.curso.springboot.springboot_web.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ParamDto {

    private String message;
    private Integer code;

    public ParamDto() {
    }

}