package com.herusantoso.registration.dtos;

import lombok.Data;

@Data
public class ResultErrorDTO {

    private String error;
    private String message;
    private Object result;

}
