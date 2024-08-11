package com.viesant.LabMedical.repositories;

import com.viesant.LabMedical.entities.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

  Optional<PacienteEntity> findByDadosPessoaisCpf(String cpf);
  Optional<PacienteEntity> findByUsuario_Id(Long usuarioId);
}
