package com.codigo.ms_sistem_sif.domain.aggregates.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FamiliaDto {

    private Long codFamilia;
    private String nomFamilia;
    private String dirFamilia;
    private String cmpFamilia;

    private Integer stdFamilia;
    private String usuaCrea;
    private Timestamp dateCreate;
    private String usuaModif;
    private Timestamp dateModif;
    private String usuaDelet;
    private Timestamp dateDelet;
    private List<IncidenciaDto> incidencias;

}
