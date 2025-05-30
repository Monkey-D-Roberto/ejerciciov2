package com.codigo.ms_sistem_sif.infrastructure.service;

import com.codigo.ms_sistem_sif.domain.aggregates.request.SignInRequest;
import com.codigo.ms_sistem_sif.domain.aggregates.request.SignUpRequest;
import com.codigo.ms_sistem_sif.domain.aggregates.response.AuthenticationResponse;
import com.codigo.ms_sistem_sif.infrastructure.entity.UsuarioEntity;

public interface AuthenticationService {
    UsuarioEntity signUpUser(SignUpRequest signUpRequest);
    UsuarioEntity signUpAdmin(SignUpRequest signUpRequest);
    AuthenticationResponse signin(SignInRequest signInRequest);
}
