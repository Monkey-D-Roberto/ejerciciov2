package com.codigo.ms_sistem_sif.infrastructure.dao;

import com.codigo.ms_sistem_sif.infrastructure.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol,Long> {
    Optional<Rol> findByNombreRol(String rol);

}
