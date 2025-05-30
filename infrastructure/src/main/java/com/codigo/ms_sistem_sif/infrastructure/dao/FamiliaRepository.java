package com.codigo.ms_sistem_sif.infrastructure.dao;

import com.codigo.ms_sistem_sif.infrastructure.entity.FamiliaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FamiliaRepository extends JpaRepository<FamiliaEntity, Long> {
    Optional<FamiliaEntity> findByCodFamilia(Long codFamilia);

}
