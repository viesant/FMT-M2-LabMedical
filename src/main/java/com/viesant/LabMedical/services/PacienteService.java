package com.viesant.LabMedical.services;

import com.sun.jdi.request.DuplicateRequestException;
import com.viesant.LabMedical.DTO.PacienteRequest;
import com.viesant.LabMedical.entities.PacienteEntity;
import com.viesant.LabMedical.entities.UsuarioEntity;
import com.viesant.LabMedical.repositories.PacienteRepository;
import com.viesant.LabMedical.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
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
    if (pacienteRepository.findByUsuario_Id(pacienteRequest.usuarioId()).isPresent() ){
      throw new DuplicateRequestException("Já existe paciente com este usuario");
    }
    if (pacienteRepository.findByDadosPessoaisCpf(pacienteRequest.dadosPessoais().cpf()).isPresent()){
      throw new DuplicateRequestException("Já existe paciente com este cpf");
    }

    PacienteEntity novoPaciente = new PacienteEntity();
    PacienteEntity.DadosPessoais dadosPessoais = new PacienteEntity.DadosPessoais();

    dadosPessoais.setNome(pacienteRequest.dadosPessoais().nome());
    dadosPessoais.setGenero(pacienteRequest.dadosPessoais().genero());
    dadosPessoais.setDataNascimento(pacienteRequest.dadosPessoais().dataNascimento());
    dadosPessoais.setCpf(pacienteRequest.dadosPessoais().cpf());
    dadosPessoais.setRg(pacienteRequest.dadosPessoais().rg());
    dadosPessoais.setEstadoCivil(pacienteRequest.dadosPessoais().estadoCivil());
    dadosPessoais.setTelefone(pacienteRequest.dadosPessoais().telefone());
    dadosPessoais.setEmail(pacienteRequest.dadosPessoais().email());
    dadosPessoais.setNaturalidade(pacienteRequest.dadosPessoais().naturalidade());
    novoPaciente.setDadosPessoais(dadosPessoais);

    PacienteEntity.Saude saude = new PacienteEntity.Saude();
    saude.setTelefoneContato(pacienteRequest.saude().telefoneContato());
    saude.setAlergias(
        pacienteRequest.saude().alergias() != null
            ? pacienteRequest.saude().alergias()
            : List.of());
    saude.setCuidados(
        pacienteRequest.saude().cuidados() != null
            ? pacienteRequest.saude().cuidados()
            : List.of());
    saude.setNomeConvenio(pacienteRequest.saude().nomeConvenio());
    saude.setNumeroConvenio(pacienteRequest.saude().numeroConvenio());
    saude.setValidadeConvenio(pacienteRequest.saude().validadeConvenio());
    novoPaciente.setSaude(saude);

    PacienteEntity.Endereco endereco = new PacienteEntity.Endereco();
    endereco.setCep(pacienteRequest.endereco().cep());
    endereco.setCidade(pacienteRequest.endereco().cidade());
    endereco.setEstado(pacienteRequest.endereco().estado());
    endereco.setLogradouro(pacienteRequest.endereco().logradouro());
    endereco.setNumero(pacienteRequest.endereco().numero());
    endereco.setComplemento(pacienteRequest.endereco().complemento());
    endereco.setBairro(pacienteRequest.endereco().bairro());
    endereco.setPontoReferencia(pacienteRequest.endereco().pontoReferencia());
    novoPaciente.setEndereco(endereco);

    novoPaciente.setUsuario(usuario.get());

    return pacienteRepository.save(novoPaciente);
  }
}
