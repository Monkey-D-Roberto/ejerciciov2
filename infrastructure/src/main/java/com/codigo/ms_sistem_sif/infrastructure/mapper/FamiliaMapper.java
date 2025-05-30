package com.codigo.ms_sistem_sif.infrastructure.mapper;
import com.codigo.ms_sistem_sif.domain.aggregates.dto.FamiliaDto;
import com.codigo.ms_sistem_sif.infrastructure.entity.FamiliaEntity;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class FamiliaMapper {
    //comentario de prueba para ver cambios
    private FamiliaMapper(){
    }
    public static FamiliaDto fromEntity(FamiliaEntity entity){
        return FamiliaDto.builder()
                .codFamilia(entity.getCodFamilia())
                .nomFamilia(entity.getNomFamilia())
                .dirFamilia(entity.getDirFamilia())
                .cmpFamilia(entity.getCmpFamilia())
                .incidencias(mapList(entity.getIncidencias(),IncidenciaMapper::fromEntity))

                .stdFamilia(entity.getStdFamilia())
                .usuaCrea(entity.getUsuaCrea())
                .dateCreate(entity.getDateCreate())
                .usuaModif(entity.getUsuaModif())
                .dateModif(entity.getDateModif())
                .usuaDelet(entity.getUsuaDelet())
                .dateDelet(entity.getDateDelet())
                .build();
    }
    public static  <T, R> List<R> mapList(List<T> list, Function<T, R> mapper) {

        return list != null
                ? list.stream().map(mapper).toList()
                : Collections.emptyList();
    }

}
