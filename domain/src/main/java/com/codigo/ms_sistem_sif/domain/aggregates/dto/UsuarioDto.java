package com.codigo.ms_sistem_sif.domain.aggregates.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDto {
    private Long codUsuario;
    private String nombre;
    private String cagUsuario;
    private Integer stdUsuario;
    private String email;
    private String password;
    private String roles;

    private List<Long> incidenciasAsignadasIds;

}
