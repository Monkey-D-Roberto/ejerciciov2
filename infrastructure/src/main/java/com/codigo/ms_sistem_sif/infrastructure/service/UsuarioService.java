package com.codigo.ms_sistem_sif.infrastructure.service;

import com.codigo.ms_sistem_sif.infrastructure.entity.UsuarioEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsuarioService {
    UserDetailsService userDetailService();
    List<UsuarioEntity> getUsuarios();
}
