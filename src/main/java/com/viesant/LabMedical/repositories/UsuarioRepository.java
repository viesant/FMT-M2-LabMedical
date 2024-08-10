package com.viesant.LabMedical.repositories;

import com.viesant.LabMedical.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
  UsuarioEntity findByNome(String nome);
}
