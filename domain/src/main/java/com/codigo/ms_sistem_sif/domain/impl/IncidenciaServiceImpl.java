package com.codigo.ms_sistem_sif.domain.impl;

import com.codigo.ms_sistem_sif.domain.aggregates.dto.IncidenciaDto;
import com.codigo.ms_sistem_sif.domain.aggregates.request.IncidenciaRequest;
import com.codigo.ms_sistem_sif.domain.ports.in.IncidenciaServiceIn;
import com.codigo.ms_sistem_sif.domain.ports.out.IncidenciaServiceOut;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidenciaServiceImpl implements IncidenciaServiceIn {

    private final IncidenciaServiceOut incidenciaServiceOut;

    public IncidenciaServiceImpl(IncidenciaServiceOut incidenciaServiceOut) {
        this.incidenciaServiceOut = incidenciaServiceOut;
    }

    @Override
    public IncidenciaDto crearIncidenciaIn(IncidenciaRequest incidenciaRequest) {
        return incidenciaServiceOut.crearIncidenciaOut(incidenciaRequest);
    }

    @Override
    public Optional<IncidenciaDto> buscarXIdIn(Long id) {
        return incidenciaServiceOut.buscarXIdOut(id);
    }

    @Override
    public List<IncidenciaDto> obtenerTodosIn() {
        return incidenciaServiceOut.obtenerTodosOut();
    }

    @Override
    public IncidenciaDto actualizarIn(Long id, IncidenciaRequest incidenciaRequest) {
        return incidenciaServiceOut.actualizarOut(id,incidenciaRequest);
    }

    @Override
    public IncidenciaDto deleteIn(Long id) {
        return incidenciaServiceOut.deleteOut(id);
    }
}
