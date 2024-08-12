package com.viesant.LabMedical.services;


import static com.viesant.LabMedical.mappers.ExameMapper.map;

import com.viesant.LabMedical.DTO.ExameRequest;
import com.viesant.LabMedical.entities.ExameEntity;
import com.viesant.LabMedical.entities.PacienteEntity;
import com.viesant.LabMedical.entities.UsuarioEntity;
import com.viesant.LabMedical.repositories.ExameRepository;
import com.viesant.LabMedical.repositories.PacienteRepository;
import com.viesant.LabMedical.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExameService {
  private final ExameRepository exameRepository;
  private final PacienteRepository pacienteRepository;
  private final UsuarioRepository usuarioRepository;

  public ExameEntity novoExame(ExameRequest exameRequest) {

    PacienteEntity paciente =
        pacienteRepository
            .findById(exameRequest.pacienteId())
            .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

    return exameRepository.save(map(exameRequest, paciente));
  }

  public ExameEntity buscaExamePorId(Long id, JwtAuthenticationToken token) {

    UsuarioEntity usuario =
        usuarioRepository
            .findByEmail(token.getName())
            .orElseThrow(() -> new EntityNotFoundException("Usuário do token não encontrado"));

    if (usuario.getPerfil().stream().anyMatch(perfil -> "PACIENTE".equals(perfil.getNome()))) {
      PacienteEntity paciente =
          pacienteRepository
              .findByUsuario_Id(usuario.getId())
              .orElseThrow(() -> new EntityNotFoundException("Usuário não associado a paciente"));

      if (!paciente.getId().equals(id)) {
        throw new EntityNotFoundException("PACIENTE só pode acessar o próprio ID.");
      }
    }

    return exameRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Exame não encontrada"));
  }

  public ExameEntity editaExamePorId(Long id, ExameRequest exameRequest) {

    ExameEntity exame = exameRepository
                                .findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Exame não encontrado"));

    PacienteEntity paciente = pacienteRepository
                                      .findById(exameRequest.pacienteId())
                                      .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

    exame.setNomeExame(exameRequest.nomeExame());
    exame.setDataExame(exameRequest.dataExame());
    exame.setHorarioExame(exameRequest.horarioExame());
    exame.setTipoExame(exameRequest.tipoExame());
    exame.setLaboratorio(exameRequest.laboratorio());
    exame.setResultados(exameRequest.resultados());
    exame.setUrlDocumento(exameRequest.urlDocumento());
    exame.setPaciente(paciente);

    return exameRepository.save(exame);
  }

  public void deletaExamePorId(Long id) {
    ExameEntity exameEntity =
        exameRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Exame não encontrado com id " + id));
    exameRepository.deleteById(id);
  }
}
