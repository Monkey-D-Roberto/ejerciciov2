package com.codigo.ms_sistem_sif.domain.aggregates.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FamiliaRequest {

    @NotNull(message = "El campo Codigo Familia es necesario.")
    private Long codFamilia;

    @NotBlank(message = "El campo dirección es necesario.")
    private String dirFamilia;

    @NotBlank(message = "El campo composicion familiar es necesario.")
    private String cmpFamilia;

}
