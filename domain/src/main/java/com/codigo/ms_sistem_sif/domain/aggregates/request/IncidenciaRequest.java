package com.codigo.ms_sistem_sif.domain.aggregates.request;

import com.codigo.ms_sistem_sif.domain.enums.EstadoIncidencia;
import com.codigo.ms_sistem_sif.domain.enums.TipoIncidencia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class IncidenciaRequest {

    @NotNull(message = "El campo Codigo de Incidencia es necesario.")
    private Long codFamilia;

    @NotNull(message = "El campo Codigo de Trabajador es necesario.")
    private Long codTrabajador;

    @NotNull(message = "El campo TipoIncidencia es necesario.")
    private TipoIncidencia tipoIncidencia;

    @NotNull(message = "El campo estado Indicendia es necesario.")
    private EstadoIncidencia estadoIncidencia;

    @NotBlank(message = "El campo descripcion es necesario.")
    private String descripcion;

    @NotNull(message = "El campo fecha de registro es necesario.")
     private LocalDateTime fechaRegistro;

}
