package com.viesant.LabMedical.repositories;

import com.viesant.LabMedical.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
  Optional<UsuarioEntity> findByNome(String nome);

  Optional<UsuarioEntity> findByEmail(String email);

  Optional<UsuarioEntity> findByCpf(String cpf);
}
