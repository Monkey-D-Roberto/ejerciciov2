package com.codigo.ms_sistem_sif.infrastructure.exceptions;

public class JsonConversionException extends RuntimeException {
    public JsonConversionException(String msj, Throwable cause){
        super(msj, cause);
    }
}
