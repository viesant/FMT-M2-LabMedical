package com.viesant.LabMedical.repositories;

import com.viesant.LabMedical.entities.PerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<PerfilEntity, Long> {
  Optional<PerfilEntity> findByNome(String nome);
}
