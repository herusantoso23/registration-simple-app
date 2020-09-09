package com.herusantoso.registration.controllers;

import com.herusantoso.registration.dtos.ObjectErrorDTO;
import com.herusantoso.registration.dtos.ResultErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class AdviceController {

    private static final String ERR_VALIDATION = "validation_error";
    private static final String NOT_ACCEPTABLE = "not_acceptable";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultErrorDTO> validationHandler(MethodArgumentNotValidException ex, HttpServletRequest request) {
        BindingResult result = ex.getBindingResult();
        List<ObjectErrorDTO> errorMessages = result.getFieldErrors()
                .stream()
                .map(objectError -> {
                    ObjectErrorDTO dto = new ObjectErrorDTO();
                    dto.setObject(objectError.getField());
                    dto.setMessage(objectError.getDefaultMessage());
                    dto.setValue(objectError.getRejectedValue());
                    return dto;
                })
                .collect(Collectors.toList());

        ResultErrorDTO response = new ResultErrorDTO();
        response.setError(ERR_VALIDATION);
        response.setMessage(errorMessages.get(0).getMessage());
        response.setResult(errorMessages);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ResultErrorDTO> httpMessageNotReadableHandler(HttpServletRequest request, Exception e) {
        ResultErrorDTO error = new ResultErrorDTO();
        error.setError(NOT_ACCEPTABLE);
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}