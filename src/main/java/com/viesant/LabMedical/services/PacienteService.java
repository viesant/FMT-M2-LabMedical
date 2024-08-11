package com.viesant.LabMedical.services;

import com.sun.jdi.request.DuplicateRequestException;
import com.viesant.LabMedical.DTO.PacienteRequest;
import com.viesant.LabMedical.entities.PacienteEntity;
import com.viesant.LabMedical.entities.UsuarioEntity;
import com.viesant.LabMedical.mappers.PacienteMapper;
import com.viesant.LabMedical.repositories.PacienteRepository;
import com.viesant.LabMedical.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PacienteService {
  private final PacienteRepository pacienteRepository;
  private final UsuarioRepository usuarioRepository;

  public PacienteEntity novoPaciente(PacienteRequest pacienteRequest) {

    Optional<UsuarioEntity> usuario = usuarioRepository.findById(pacienteRequest.usuarioId());
    if (usuario.isEmpty()) {
      throw new EntityNotFoundException("Não existe usuario");
    } else if (usuario.get().getPerfil().stream()
        .noneMatch(perfil -> "PACIENTE".equals(perfil.getNome()))) {

      throw new EntityNotFoundException("Usuário não tem o perfil de paciente.");
    }
    if (pacienteRepository.findByUsuario_Id(pacienteRequest.usuarioId()).isPresent()) {
      throw new DuplicateRequestException("Já existe paciente com este usuario");
    }
    if (pacienteRepository
        .findByDadosPessoaisCpf(pacienteRequest.dadosPessoais().cpf())
        .isPresent()) {
      throw new DuplicateRequestException("Já existe paciente com este cpf");
    }

    PacienteEntity novoPaciente = PacienteMapper.map(pacienteRequest);

    novoPaciente.setUsuario(usuario.get());

    return pacienteRepository.save(novoPaciente);
  }

  public PacienteEntity buscaPacientePorId(Long id, JwtAuthenticationToken token) {

    UsuarioEntity usuario =
        usuarioRepository
            .findByEmail(token.getName())
            .orElseThrow(() -> new EntityNotFoundException("Usuario do token não encontrado"));

    if (usuario.getPerfil().stream().anyMatch(perfil -> "PACIENTE".equals(perfil.getNome()))) {
      PacienteEntity paciente =
          pacienteRepository
              .findByUsuario_Id(usuario.getId())
              .orElseThrow(() -> new EntityNotFoundException("Usuário não associado a paciente"));
      if (!paciente.getId().equals(id)) {
        throw new EntityNotFoundException("PACIENTE só pode acessar o próprio ID.");
      }
    }

    return pacienteRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public PacienteEntity editaPacientePorId(Long id, PacienteRequest pacienteRequest) {
    PacienteEntity pacienteEntity =
        pacienteRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com id " + id));

    PacienteEntity pacienteEdicao = PacienteMapper.map(pacienteRequest);
    pacienteEntity.setDadosPessoais(pacienteEdicao.getDadosPessoais());
    pacienteEntity.setSaude(pacienteEdicao.getSaude());
    pacienteEntity.setEndereco(pacienteEdicao.getEndereco());

    return pacienteRepository.save(pacienteEntity);
  }

  public void deletaPacientePorId(Long id) {
    PacienteEntity pacienteEntity =
            pacienteRepository
                    .findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com id " + id));
    pacienteRepository.deleteById(id);

    return;
  }
}
