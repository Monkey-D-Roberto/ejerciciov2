package com.codigo.ms_sistem_sif.infrastructure.exceptions;

public class ResponseValidationException extends RuntimeException {
    public ResponseValidationException(String message) {
        super(message);
    }
}
