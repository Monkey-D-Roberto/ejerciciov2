package com.codigo.ms_sistem_sif.application.controller;

import com.codigo.ms_sistem_sif.domain.aggregates.request.SignInRequest;
import com.codigo.ms_sistem_sif.domain.aggregates.request.SignUpRequest;
import com.codigo.ms_sistem_sif.domain.aggregates.response.AuthenticationResponse;
import com.codigo.ms_sistem_sif.infrastructure.entity.UsuarioEntity;
import com.codigo.ms_sistem_sif.infrastructure.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/autenticacion")
@RequiredArgsConstructor
public class AutenticacionController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signupuser")
    public ResponseEntity<UsuarioEntity> signUpUser(@Valid @RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUpUser(signUpRequest));
    }
    @PostMapping("/signupadmin")
    public ResponseEntity<UsuarioEntity> signUpAdmin(@Valid @RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUpAdmin(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signin(@Valid @RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signin(signInRequest));
    }
}
