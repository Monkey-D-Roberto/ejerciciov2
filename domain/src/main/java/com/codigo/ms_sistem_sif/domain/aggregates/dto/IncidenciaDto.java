package com.codigo.ms_sistem_sif.domain.aggregates.dto;

import com.codigo.ms_sistem_sif.domain.enums.EstadoIncidencia;
import com.codigo.ms_sistem_sif.domain.enums.TipoIncidencia;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IncidenciaDto {

    private Long codIncidencia;
    private FamiliaDto familia;
    private Long codFamilia;
    private UsuarioDto usuario;
    private Long codTrabajador;
    private TipoIncidencia tipoIncidencia;
    private String descripcion;
    private LocalDateTime fechaRegistro;
    private EstadoIncidencia estadoIncidencia;
    private  Integer stdIncidencia;

    private String usuaCrea;
    private Timestamp dateCreate;
    private String usuaModif;
    private Timestamp dateModif;
    private String usuaDelet;
    private Timestamp dateDelet;

}
