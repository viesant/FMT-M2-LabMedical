package com.viesant.LabMedical.repositories;

import com.viesant.LabMedical.entities.PacienteEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

  Optional<PacienteEntity> findByDadosPessoaisCpf(String cpf);

  Optional<PacienteEntity> findByUsuario_Id(Long usuarioId);

  Page<PacienteEntity> findByDadosPessoaisNomeContainingIgnoreCaseAndDadosPessoaisTelefoneContainingIgnoreCaseAndDadosPessoaisEmailContainingIgnoreCase(
          String nome, String telefone, String email, Pageable pageable);
//  Page<PacienteEntity>
//      findByNomeContainingIgnoreCaseAndTelefoneContainingAndEmailContainingIgnoreCase(
//          String nome, String telefone, String email, Pageable pageable);
  //  Page<PacienteEntity>
  // findByNomeContainingIgnoreCaseAndTelefoneContainingAndEmailContainingIgnoreCase(
  //          String nome, String telefone, String email, Pageable paginacao
  //  );

}
