package com.codigo.ms_sistem_sif.application.controller;

import com.codigo.ms_sistem_sif.domain.aggregates.dto.IncidenciaDto;
import com.codigo.ms_sistem_sif.domain.aggregates.request.IncidenciaRequest;
import com.codigo.ms_sistem_sif.domain.ports.in.IncidenciaServiceIn;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ms-sistem-sif/v1/incidencia")
@AllArgsConstructor
public class IncidenciaController {

    private final IncidenciaServiceIn incidenciaServiceIn;

    @PostMapping("/create")
    public ResponseEntity<IncidenciaDto> registrar(@Valid @RequestBody IncidenciaRequest incidenciaRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(incidenciaServiceIn.crearIncidenciaIn(incidenciaRequest));
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<IncidenciaDto> buscarXid(@PathVariable Long id){
        return incidenciaServiceIn.buscarXIdIn(id).map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<IncidenciaDto>> buscartodos(){
        return ResponseEntity.ok(incidenciaServiceIn.obtenerTodosIn());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<IncidenciaDto> actualizar(@PathVariable Long id,@Valid @RequestBody IncidenciaRequest incidenciaRequest){
        return ResponseEntity.ok(incidenciaServiceIn.actualizarIn(id,incidenciaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<IncidenciaDto> delete(@PathVariable Long id){
        return ResponseEntity.ok(incidenciaServiceIn.deleteIn(id));
    }
}
