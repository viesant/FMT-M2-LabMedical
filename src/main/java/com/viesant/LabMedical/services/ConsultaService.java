package com.viesant.LabMedical.services;

import static com.viesant.LabMedical.mappers.ConsultaMapper.map;

import com.viesant.LabMedical.DTO.ConsultaRequest;
import com.viesant.LabMedical.entities.ConsultaEntity;
import com.viesant.LabMedical.entities.PacienteEntity;
import com.viesant.LabMedical.entities.UsuarioEntity;
import com.viesant.LabMedical.repositories.ConsultaRepository;
import com.viesant.LabMedical.repositories.PacienteRepository;
import com.viesant.LabMedical.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConsultaService {
  private final ConsultaRepository consultaRepository;
  private final PacienteRepository pacienteRepository;
  private final UsuarioRepository usuarioRepository;

  public ConsultaEntity novoConsulta(ConsultaRequest consultaRequest) {

    PacienteEntity paciente =
        pacienteRepository
            .findById(consultaRequest.pacienteId())
            .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

    return consultaRepository.save(map(consultaRequest, paciente));
  }

  public ConsultaEntity buscaConsultaPorId(Long id, JwtAuthenticationToken token) {

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

    return consultaRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada"));
  }

  public ConsultaEntity editaConsultaPorId(Long id, ConsultaRequest consultaRequest) {

    ConsultaEntity consulta =
        consultaRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada"));

    consulta.setMotivoConsulta(consultaRequest.motivoConsulta());
    consulta.setDataConsulta(consultaRequest.dataConsulta());
    consulta.setHorarioConsulta(consultaRequest.horarioConsulta());
    consulta.setDescricaoProblema(consultaRequest.descricaoProblema());
    consulta.setMedicacaoReceitada(consultaRequest.medicacaoReceitada());
    consulta.setDosagemPrecaucoes(consultaRequest.dosagemPrecaucoes());

    PacienteEntity paciente =
        pacienteRepository
            .findById(consultaRequest.pacienteId())
            .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

    consulta.setPaciente(paciente);

    return consultaRepository.save(consulta);
  }

  public void deletaConsultaPorId(Long id) {
    ConsultaEntity consultaEntity =
        consultaRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrado com id " + id));
    consultaRepository.deleteById(id);
  }
}
