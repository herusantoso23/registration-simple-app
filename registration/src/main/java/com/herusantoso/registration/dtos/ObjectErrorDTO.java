package com.herusantoso.registration.dtos;

import lombok.Data;

@Data
public class ObjectErrorDTO {

    private String object;
    private String message;
    private Object value;

}
