package com.codigo.ms_sistem_sif.infrastructure.mapper;
import com.codigo.ms_sistem_sif.domain.aggregates.dto.IncidenciaDto;
import com.codigo.ms_sistem_sif.infrastructure.entity.IncidenciaEntity;

public class IncidenciaMapper {
    private IncidenciaMapper(){ }
    public static IncidenciaDto fromEntity(IncidenciaEntity entity){
        return IncidenciaDto.builder()
                .codIncidencia(entity.getCodIncidencia())
                .codFamilia(entity.getFamilia() == null ? null : entity.getFamilia().getCodFamilia())
                .codTrabajador(entity.getTrabajador()== null?null:entity.getTrabajador().getCodUsuario())
                .tipoIncidencia(entity.getTipoIncidencia())
                .descripcion(entity.getDescripcion())
                .fechaRegistro(entity.getFechaRegistro())
                .estadoIncidencia(entity.getEstadoIncidencia())
                .stdIncidencia(entity.getStdIncidencia())
                .usuaCrea(entity.getUsuaCrea())
                .dateCreate(entity.getDateCreate())
                .usuaModif(entity.getUsuaModif())
                .dateModif(entity.getDateModif())
                .usuaDelet(entity.getUsuaDelet())
                .dateDelet(entity.getDateDelet())
                .build();
    }
    /*public static IncidenciaDto fromEntity(IncidenciaEntity entity){
        return IncidenciaDto.builder()
                .codIncidencia(entity.getCodIncidencia())
                .tipoIncidencia(entity.getTipoIncidencia())
                .estadoIncidencia(entity.getEstadoIncidencia())
                .fechaRegistro(entity.getFechaRegistro())
                .stdIncidencia(entity.getStdIncidencia())

                .usuaCrea(entity.getUsuaCrea())
                .dateCreate(entity.getDateCreate())
                .usuaModif(entity.getUsuaModif())
                .dateModif(entity.getDateModif())
                .usuaDelet(entity.getUsuaDelet())
                .dateDelet(entity.getDateDelet())
                .build();
    }
    */
}
