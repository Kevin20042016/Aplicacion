package com.fitnessapp.exception;

public class CuentaNoVerificadaException extends RuntimeException {
    public CuentaNoVerificadaException() {
        super("Debes verificar tu correo electrónico antes de iniciar sesión.");
    }
}
