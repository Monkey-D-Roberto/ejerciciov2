package com.codigo.ms_sistem_sif.infrastructure.adapters;

import com.codigo.ms_sistem_sif.domain.aggregates.constants.Constant;
import com.codigo.ms_sistem_sif.domain.aggregates.dto.FamiliaDto;
import com.codigo.ms_sistem_sif.domain.aggregates.dto.ReniecDto;
import com.codigo.ms_sistem_sif.domain.aggregates.request.FamiliaRequest;
import com.codigo.ms_sistem_sif.domain.aggregates.request.SignInRequest;
import com.codigo.ms_sistem_sif.domain.ports.out.FamiliaServiceOut;
import com.codigo.ms_sistem_sif.infrastructure.client.ClientReniec;
import com.codigo.ms_sistem_sif.infrastructure.dao.FamiliaRepository;
import com.codigo.ms_sistem_sif.infrastructure.entity.FamiliaEntity;
import com.codigo.ms_sistem_sif.infrastructure.entity.UsuarioEntity;
import com.codigo.ms_sistem_sif.infrastructure.exceptions.ResponseValidationException;
import com.codigo.ms_sistem_sif.infrastructure.mapper.FamiliaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor

public class FamiliaAdapter implements FamiliaServiceOut {

    private final FamiliaRepository familiaRepository;
    private final ClientReniec clientReniec;


    @Value("${token.reniec}")
    private String tokenReniec;

    @Override
    public FamiliaDto crearFamiliaOut(FamiliaRequest familiaRequest) {
        FamiliaEntity familiaEntity = getEntity(new FamiliaEntity(),familiaRequest,false,null);
        return FamiliaMapper.fromEntity(familiaRepository.save(familiaEntity));
    }

    private FamiliaEntity getEntity(FamiliaEntity entity,FamiliaRequest familiaRequest, boolean actualiza, Long id){
        ReniecDto reniecDto= getExecReniec(String.valueOf(familiaRequest.getCodFamilia()));


        entity.setNomFamilia(reniecDto.getNombreCompleto());
        entity.setDirFamilia(familiaRequest.getDirFamilia());
        entity.setCmpFamilia(familiaRequest.getCmpFamilia());
        entity.setCodFamilia(familiaRequest.getCodFamilia());


        if(actualiza){
            entity.setCodFamilia(id);
            entity.setUsuaModif(String.valueOf(((UsuarioEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getNomUsuario()));
            entity.setDateModif(getTimestamp());

        }else{
            entity.setStdFamilia(Constant.STATUS_ACTIVE);
            entity.setUsuaCrea(String.valueOf(((UsuarioEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getNomUsuario()));
            entity.setDateCreate(getTimestamp());
        }

        return entity;
    }
    @Override
    public Optional<FamiliaDto> buscarXIdOut(Long id) {
        // Busca directamente en el repositorio y mapear a DTO
        FamiliaDto familiaDto = FamiliaMapper.fromEntity(
                familiaRepository.findById(id).orElseThrow(
                        () -> new RuntimeException("Familia no encontrada con ID: " + id)
                )
        );
        return Optional.of(familiaDto);
    }

    @Override
    public List<FamiliaDto> obtenerTodosOut() {
        List<FamiliaDto> listaDto = new ArrayList<>();
        List<FamiliaEntity> entidades = familiaRepository.findAll();
        for (FamiliaEntity dato :entidades){
            listaDto.add(FamiliaMapper.fromEntity(dato));
        }
        return listaDto;
    }

    @Override
    public FamiliaDto actualizarOut(Long id, FamiliaRequest familiaRequest) {

        Optional<FamiliaEntity> datoExtraido = familiaRepository.findById(id);
        if (datoExtraido.isPresent()) {
            FamiliaEntity familiaEntity = getEntity(datoExtraido.get(), familiaRequest, true, id);
            return FamiliaMapper.fromEntity(familiaRepository.save(familiaEntity));
        }else{
            throw new ResponseValidationException("Familia no encontrada. ");
        }
    }

    @Override
    public FamiliaDto deleteOut(Long id) {
        Optional<FamiliaEntity> datoExtraido = familiaRepository.findById(id);
        if(datoExtraido.isPresent()){
            datoExtraido.get().setStdFamilia(0);
            datoExtraido.get().setUsuaDelet(Constant.USU_ADMIN);
            datoExtraido.get().setDateDelet(getTimestamp());
            return FamiliaMapper.fromEntity(familiaRepository.save(datoExtraido.get()));
        }else {
            throw new ResponseValidationException("Familia no encontrada.");
        }
    }

    private ReniecDto getExecReniec(String numDoc){
        String authorization = "Bearer " + tokenReniec;
        return clientReniec.getInfoReniec(numDoc,authorization);
    }
    private Timestamp getTimestamp(){
        long currenTIme = System.currentTimeMillis();
        return new Timestamp(currenTIme);
    }
}
