package com.codigo.ms_sistem_sif.infrastructure.mapper;

import com.codigo.ms_sistem_sif.domain.aggregates.dto.UsuarioDto;
import com.codigo.ms_sistem_sif.infrastructure.entity.UsuarioEntity;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class UsuarioMapper {
    private  UsuarioMapper(){
    }
    public static UsuarioDto fromEntity(UsuarioEntity entity){
        return  UsuarioDto.builder()
                .codUsuario(entity.getCodUsuario())
                .nombre(entity.getNomUsuario())
                .cagUsuario(entity.getCagUsuario())
                .incidenciasAsignadasIds(mapList(entity.getIncidenciasAsignadas(),(incidenciaEntity -> incidenciaEntity.getCodIncidencia())))
                .stdUsuario(entity.getStdUsuario())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .roles(entity.getRoles().toString())
                .build();
    }
    public static  <T, R> List<R> mapList(List<T> list, Function<T, R> mapper) {

        return list != null
                ? list.stream().map(mapper).toList()
                : Collections.emptyList();
    }
}
