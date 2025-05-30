package com.codigo.ms_sistem_sif.domain.ports.in;


import com.codigo.ms_sistem_sif.domain.aggregates.dto.IncidenciaDto;
import com.codigo.ms_sistem_sif.domain.aggregates.request.IncidenciaRequest;

import java.util.List;
import java.util.Optional;

public interface IncidenciaServiceIn {
    IncidenciaDto crearIncidenciaIn(IncidenciaRequest incidenciaRequest);
    Optional<IncidenciaDto> buscarXIdIn(Long id);
    List<IncidenciaDto> obtenerTodosIn();
    IncidenciaDto actualizarIn(Long id, IncidenciaRequest incidenciaRequest);
    IncidenciaDto deleteIn(Long id);
}
