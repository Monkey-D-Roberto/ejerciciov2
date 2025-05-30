package com.codigo.ms_sistem_sif.domain.ports.out;

import com.codigo.ms_sistem_sif.domain.aggregates.dto.FamiliaDto;
import com.codigo.ms_sistem_sif.domain.aggregates.request.FamiliaRequest;


import java.util.List;
import java.util.Optional;

public interface FamiliaServiceOut {
    FamiliaDto crearFamiliaOut(FamiliaRequest familiaRequest);
    Optional<FamiliaDto> buscarXIdOut(Long id);
    List<FamiliaDto> obtenerTodosOut();
    FamiliaDto actualizarOut(Long id, FamiliaRequest familiaRequest);
    FamiliaDto deleteOut(Long id);
}
