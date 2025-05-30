package com.codigo.ms_sistem_sif.infrastructure.dao;

import com.codigo.ms_sistem_sif.infrastructure.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {
    Optional<UsuarioEntity> findByEmail(String email);
    Optional<UsuarioEntity> findByCodUsuario(Long codUsuario);
}
