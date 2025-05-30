package com.codigo.ms_sistem_sif.domain.ports.out;

import com.codigo.ms_sistem_sif.domain.aggregates.dto.IncidenciaDto;
import com.codigo.ms_sistem_sif.domain.aggregates.request.IncidenciaRequest;

import java.util.List;
import java.util.Optional;

public interface IncidenciaServiceOut {
    IncidenciaDto crearIncidenciaOut(IncidenciaRequest incidenciaRequest);
    Optional<IncidenciaDto> buscarXIdOut(Long id);
    List<IncidenciaDto> obtenerTodosOut();
    IncidenciaDto actualizarOut(Long id, IncidenciaRequest incidenciaRequest);
    IncidenciaDto deleteOut(Long id);
}
