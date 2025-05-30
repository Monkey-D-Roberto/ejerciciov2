package com.codigo.ms_sistem_sif.domain.aggregates.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    @NotNull(message = "El campo codigo es necesario.")
    private Long codUsuario;

    @NotBlank(message = "El campo caargo es necesario.")
    private String cargo;

    @NotBlank(message = "El campo email es necesario.")
    private String email;

    @NotBlank(message = "El campo password es necesario.")
    private String password;
}

