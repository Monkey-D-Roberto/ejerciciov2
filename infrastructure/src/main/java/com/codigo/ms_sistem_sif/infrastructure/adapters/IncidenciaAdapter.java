package com.codigo.ms_sistem_sif.infrastructure.adapters;

import com.codigo.ms_sistem_sif.domain.aggregates.constants.Constant;
import com.codigo.ms_sistem_sif.domain.aggregates.dto.FamiliaDto;
import com.codigo.ms_sistem_sif.domain.aggregates.dto.IncidenciaDto;
import com.codigo.ms_sistem_sif.domain.aggregates.request.IncidenciaRequest;
import com.codigo.ms_sistem_sif.domain.ports.out.IncidenciaServiceOut;
import com.codigo.ms_sistem_sif.infrastructure.dao.FamiliaRepository;
import com.codigo.ms_sistem_sif.infrastructure.dao.IncidenciaRepository;
import com.codigo.ms_sistem_sif.infrastructure.dao.UsuarioRepository;
import com.codigo.ms_sistem_sif.infrastructure.entity.FamiliaEntity;
import com.codigo.ms_sistem_sif.infrastructure.entity.IncidenciaEntity;
import com.codigo.ms_sistem_sif.infrastructure.entity.UsuarioEntity;
import com.codigo.ms_sistem_sif.infrastructure.exceptions.ResponseValidationException;
import com.codigo.ms_sistem_sif.infrastructure.mapper.IncidenciaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class IncidenciaAdapter implements IncidenciaServiceOut {

    private final IncidenciaRepository incidenciaRepository;
    private  final FamiliaRepository familiaRepository;
    private final UsuarioRepository usuarioRepository;
    @Override
    public IncidenciaDto crearIncidenciaOut(IncidenciaRequest incidenciaRequest) {

        IncidenciaEntity incidenciaEntity = getEntity(incidenciaRequest,false,null);
        return IncidenciaMapper.fromEntity(incidenciaRepository.save(incidenciaEntity));
    }
    private IncidenciaEntity getEntity(IncidenciaRequest incidenciaRequest, boolean actualiza, Long id){
        IncidenciaEntity entity = new IncidenciaEntity();
        entity.setTipoIncidencia(incidenciaRequest.getTipoIncidencia());
        entity.setEstadoIncidencia(incidenciaRequest.getEstadoIncidencia());
        entity.setDescripcion(incidenciaRequest.getDescripcion());
        entity.setFechaRegistro(incidenciaRequest.getFechaRegistro());
        

        FamiliaEntity familiaEntity=familiaRepository.findByCodFamilia(incidenciaRequest.getCodFamilia()).orElseThrow(() -> {
            throw new ResponseValidationException("familia no encontrada");
        });
            entity.setFamilia(familiaEntity);

        UsuarioEntity usuarioEntity=usuarioRepository.findByCodUsuario(incidenciaRequest.getCodTrabajador()).orElseThrow(() -> {
            throw new ResponseValidationException("Trabajador no encontrado");
        });
            entity.setTrabajador(usuarioEntity);
        if(actualiza){
            entity.setCodIncidencia(id);
            entity.setUsuaModif(String.valueOf(((UsuarioEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getNomUsuario()));
            entity.setDateModif(getTimestamp());

        }else{
            entity.setStdIncidencia(Constant.STATUS_ACTIVE);
            entity.setUsuaModif(String.valueOf(((UsuarioEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getNomUsuario()));
            entity.setDateCreate(getTimestamp());
        }
        return entity;
    }

    private Timestamp getTimestamp(){
        long currenTIme = System.currentTimeMillis();
        return new Timestamp(currenTIme);
    }

    @Override
    public Optional<IncidenciaDto> buscarXIdOut(Long id) {
        IncidenciaDto incidenciaDto = IncidenciaMapper.fromEntity(
                incidenciaRepository.findById(id).orElseThrow(
                        () -> new RuntimeException("Incidencia no encontrada con ID: " + id)
                )
        );
        return Optional.of(incidenciaDto);
    }

    @Override
    public List<IncidenciaDto> obtenerTodosOut() {
        List<IncidenciaDto> listaDto = new ArrayList<>();
        List<IncidenciaEntity> entidades = incidenciaRepository.findAll();
        for (IncidenciaEntity dato :entidades){
            listaDto.add(IncidenciaMapper.fromEntity(dato));
        }
        return listaDto;
    }

    @Override
    public IncidenciaDto actualizarOut(Long id, IncidenciaRequest incidenciaRequest) {
        Optional<IncidenciaEntity> datoExtraido = incidenciaRepository.findById(id);
        if(datoExtraido.isPresent()){
            IncidenciaEntity incidenciaEntity = getEntity(incidenciaRequest,true, id);
            return  IncidenciaMapper.fromEntity(incidenciaRepository.save(incidenciaEntity));
        }else {
            throw new ResponseValidationException("Incidencia no encontrada :( ");
        }
    }

    @Override
    public IncidenciaDto deleteOut(Long id) {
        Optional<IncidenciaEntity> datoExtraido = incidenciaRepository.findById(id);
        if(datoExtraido.isPresent()){
            datoExtraido.get().setStdIncidencia(0);
            datoExtraido.get().setUsuaDelet(((UsuarioEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getNomUsuario());
            datoExtraido.get().setDateDelet(getTimestamp());
            return IncidenciaMapper.fromEntity(incidenciaRepository.save(datoExtraido.get()));
        }else {
            throw new ResponseValidationException("Incidencia no encontrada :( ");
        }
    }
}
