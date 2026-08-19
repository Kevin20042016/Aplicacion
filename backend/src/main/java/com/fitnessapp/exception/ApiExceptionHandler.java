package com.fitnessapp.exception;

import com.fitnessapp.dto.ErrorMensajeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CredencialesInvalidasException.class)
    public ResponseEntity<ErrorMensajeDTO> credencialesInvalidas(CredencialesInvalidasException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorMensajeDTO(ex.getMessage()));
    }

    @ExceptionHandler(CuentaNoVerificadaException.class)
    public ResponseEntity<ErrorMensajeDTO> cuentaNoVerificada(CuentaNoVerificadaException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorMensajeDTO(ex.getMessage()));
    }
}
