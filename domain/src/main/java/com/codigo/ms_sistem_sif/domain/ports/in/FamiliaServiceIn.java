package com.codigo.ms_sistem_sif.domain.ports.in;

import com.codigo.ms_sistem_sif.domain.aggregates.dto.FamiliaDto;
import com.codigo.ms_sistem_sif.domain.aggregates.request.FamiliaRequest;


import java.util.List;
import java.util.Optional;

public interface FamiliaServiceIn {
    FamiliaDto crearFamiliaIn(FamiliaRequest familiaRequest);
    Optional<FamiliaDto> buscarXIdIn(Long id);
    List<FamiliaDto> obtenerTodosIn();
    FamiliaDto actualizarIn(Long id, FamiliaRequest familiaRequest);
    FamiliaDto deleteIn(Long id);
}
