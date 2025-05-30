package com.codigo.ms_sistem_sif.infrastructure.adapters;

import com.codigo.ms_sistem_sif.infrastructure.dao.UsuarioRepository;
import com.codigo.ms_sistem_sif.infrastructure.entity.UsuarioEntity;
import com.codigo.ms_sistem_sif.infrastructure.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    @Override
    public UserDetailsService userDetailService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return usuarioRepository.findByEmail(username).orElseThrow(()->
                        new UsernameNotFoundException("Usuario no Encontrado"));
            }
        };
    }

    @Override
    public List<UsuarioEntity> getUsuarios() {
        return usuarioRepository.findAll();
    }
}
