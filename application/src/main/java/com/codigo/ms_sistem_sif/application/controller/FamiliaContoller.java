package com.codigo.ms_sistem_sif.application.controller;

import com.codigo.ms_sistem_sif.domain.aggregates.dto.FamiliaDto;
import com.codigo.ms_sistem_sif.domain.aggregates.request.FamiliaRequest;
import com.codigo.ms_sistem_sif.domain.ports.in.FamiliaServiceIn;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ms-sistem-sif/v1/familia")
@AllArgsConstructor
public class FamiliaContoller {

    private final FamiliaServiceIn familiaServiceIn;

    @PostMapping("/create")
    public ResponseEntity<FamiliaDto> registrar(@Valid @RequestBody FamiliaRequest familiaRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(familiaServiceIn.crearFamiliaIn(familiaRequest));
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<FamiliaDto> buscarXid(@PathVariable Long id){
        return familiaServiceIn.buscarXIdIn(id).map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<FamiliaDto>> buscartodos(){
        return ResponseEntity.ok(familiaServiceIn.obtenerTodosIn());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<FamiliaDto> actualizar(@PathVariable Long id,@Valid  @RequestBody FamiliaRequest familiaRequest){
        return ResponseEntity.ok(familiaServiceIn.actualizarIn(id,familiaRequest));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<FamiliaDto> delete(@PathVariable Long id){
        return ResponseEntity.ok(familiaServiceIn.deleteIn(id));

    }
}
