package com.viesant.LabMedical.services;

import static com.viesant.LabMedical.mappers.PacienteMapper.map;

import com.sun.jdi.request.DuplicateRequestException;
import com.viesant.LabMedical.DTO.PacienteGetRequest;
import com.viesant.LabMedical.DTO.PacienteRequest;
import com.viesant.LabMedical.DTO.PacienteResponse;
import com.viesant.LabMedical.entities.PacienteEntity;
import com.viesant.LabMedical.entities.UsuarioEntity;
import com.viesant.LabMedical.repositories.PacienteRepository;
import com.viesant.LabMedical.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    PacienteEntity novoPaciente = map(pacienteRequest);

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

    PacienteEntity pacienteEdicao = map(pacienteRequest);
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
  }
//
//  public Page<PacienteResponse> listaPacientes(PacienteGetRequest filtros, Pageable paginacao) {
//    String filtroNome = filtros.nome() != null ? filtros.nome() : "";
//    String filtroTelefone = filtros.telefone() != null ? filtros.telefone() : "";
//    String filtroEmail = filtros.email() != null ? filtros.email() : "";
//
//    Page<PacienteEntity> pacientesPage =
//        pacienteRepository
//            .findByDadosPessoaisNomeContainingIgnoreCaseAndDadosPessoaisTelefoneContainingIgnoreCaseAndDadosPessoaisEmailContainingIgnoreCase(
//                filtroNome, filtroTelefone, filtroEmail, paginacao);
//
//    return map(pacientesPage);
//  }

  public Page<PacienteResponse> listaPaginada(Pageable paginacao) {

    return
            pacienteRepository.findAll(PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize()))
                    .map(
                            paciente-> new PacienteResponse(
                                    paciente.getDadosPessoais().getNome(),
                                    55,
                                    paciente.getDadosPessoais().getTelefone(),
                                    paciente.getSaude().getNomeConvenio()
                                    )
                    );

//                    .stream()
//                   .map(page -> new PacienteResponse(
//                           page.getDadosPessoais().getNome(),
//                           55,
//                           page.getDadosPessoais().getTelefone(),
//                           page.getSaude().getNomeConvenio()
//                   ));
  }
  //  public Page<PacienteResponse> listaPacientes(PacienteGetRequest filtros, Pageable paginacao) {
  //
  //    String filtroNome = filtros.nome() != null ? filtros.nome() : "";
  //    String filtroTelefone = filtros.telefone() != null ? filtros.telefone() : "";
  //    String filtroEmail = filtros.email() != null ? filtros.telefone() : "";
  //    return map(
  //        pacienteRepository
  //            .findByNomeContainingIgnoreCaseAndTelefoneContainingAndEmailContainingIgnoreCase(
  //                filtroNome, filtroTelefone, filtroEmail, paginacao));
  //  }
}
