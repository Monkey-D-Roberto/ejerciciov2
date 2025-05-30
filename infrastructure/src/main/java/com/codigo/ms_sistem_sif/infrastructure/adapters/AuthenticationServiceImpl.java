package com.codigo.ms_sistem_sif.infrastructure.adapters;

import com.codigo.ms_sistem_sif.domain.aggregates.constants.Constant;
import com.codigo.ms_sistem_sif.domain.aggregates.dto.ReniecDto;
import com.codigo.ms_sistem_sif.domain.aggregates.request.SignInRequest;
import com.codigo.ms_sistem_sif.domain.aggregates.request.SignUpRequest;
import com.codigo.ms_sistem_sif.domain.aggregates.response.AuthenticationResponse;
import com.codigo.ms_sistem_sif.infrastructure.client.ClientReniec;
import com.codigo.ms_sistem_sif.infrastructure.dao.RolRepository;
import com.codigo.ms_sistem_sif.infrastructure.dao.UsuarioRepository;
import com.codigo.ms_sistem_sif.infrastructure.entity.Rol;
import com.codigo.ms_sistem_sif.infrastructure.entity.Role;
import com.codigo.ms_sistem_sif.infrastructure.entity.UsuarioEntity;
import com.codigo.ms_sistem_sif.infrastructure.service.AuthenticationService;
import com.codigo.ms_sistem_sif.infrastructure.service.JWTService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final ClientReniec clientReniec;

    @Value("${token.reniec}")
    private String tokenReniec;
    @Transactional
    @Override
    public UsuarioEntity signUpUser(SignUpRequest signUpRequest) {

        ReniecDto reniecDto= getReniec(String.valueOf(signUpRequest.getCodUsuario()));

        UsuarioEntity usuario = new UsuarioEntity();

        usuario.setCodUsuario(signUpRequest.getCodUsuario());

        usuario.setNomUsuario(reniecDto.getNombreCompleto());
        usuario.setCagUsuario(signUpRequest.getCargo());
        usuario.setStdUsuario(Constant.STATUS_ACTIVE);
        usuario.setEmail(signUpRequest.getEmail());
        Set<Rol> assignedRoles = new HashSet<>();
        Rol userRole = rolRepository.findByNombreRol(Role.USER.name()).orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
        assignedRoles.add(userRole);
        usuario.setRoles(assignedRoles);
        usuario.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioEntity signUpAdmin(SignUpRequest signUpRequest) {

        ReniecDto reniecDto= getReniec(String.valueOf(signUpRequest.getCodUsuario()));

        UsuarioEntity usuario = new UsuarioEntity();

        usuario.setCodUsuario(signUpRequest.getCodUsuario());
        usuario.setNomUsuario(reniecDto.getNombreCompleto());
        usuario.setCagUsuario(signUpRequest.getCargo());
        usuario.setStdUsuario(Constant.STATUS_ACTIVE);
        usuario.setEmail(signUpRequest.getEmail());
        Set<Rol> assignedRoles = new HashSet<>();
        Rol userRole = rolRepository.findByNombreRol(Role.ADMIN.name()).orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
        assignedRoles.add(userRole);
        usuario.setRoles(assignedRoles);
        usuario.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public AuthenticationResponse signin(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getEmail(),signInRequest.getPassword()));
        var user = usuarioRepository.findByEmail(signInRequest.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("Email no valido"));

        var jwt = jwtService.generateToken(user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwt);
        return authenticationResponse;
    }

    private ReniecDto getReniec(String identificationNumber){
        String authorization = "Bearer " + tokenReniec;
        return clientReniec.getInfoReniec(identificationNumber, authorization);
    }
}
