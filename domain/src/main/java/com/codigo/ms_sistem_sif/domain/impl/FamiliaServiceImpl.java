package com.codigo.ms_sistem_sif.domain.impl;

import com.codigo.ms_sistem_sif.domain.aggregates.dto.FamiliaDto;
import com.codigo.ms_sistem_sif.domain.aggregates.request.FamiliaRequest;
import com.codigo.ms_sistem_sif.domain.ports.in.FamiliaServiceIn;
import com.codigo.ms_sistem_sif.domain.ports.out.FamiliaServiceOut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FamiliaServiceImpl implements FamiliaServiceIn {

    private final FamiliaServiceOut familiaServiceOut;

    public FamiliaServiceImpl(FamiliaServiceOut familiaServiceOut){
        this.familiaServiceOut = familiaServiceOut;
    }

    @Override
    public FamiliaDto crearFamiliaIn(FamiliaRequest familiaRequest) {
        return familiaServiceOut.crearFamiliaOut(familiaRequest);
    }

    @Override
    public Optional<FamiliaDto> buscarXIdIn(Long id) {
        return familiaServiceOut.buscarXIdOut(id);
    }

    @Override
    public List<FamiliaDto> obtenerTodosIn() {
        return familiaServiceOut.obtenerTodosOut();
    }

    @Override
    public FamiliaDto actualizarIn(Long id, FamiliaRequest familiaRequest) {
        return familiaServiceOut.actualizarOut(id,familiaRequest);
    }

    @Override
    public FamiliaDto deleteIn(Long id) {
        return familiaServiceOut.deleteOut(id);
    }
}
