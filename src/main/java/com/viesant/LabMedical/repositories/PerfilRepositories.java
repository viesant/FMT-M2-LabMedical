package com.viesant.LabMedical.repositories;

import com.viesant.LabMedical.entities.PerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepositories extends JpaRepository<PerfilEntity, Long> {
  PerfilEntity findByNome(String nome);
}
