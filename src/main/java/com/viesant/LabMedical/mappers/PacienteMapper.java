package com.viesant.LabMedical.mappers;

import com.viesant.LabMedical.DTO.PacienteRequest;
import com.viesant.LabMedical.entities.PacienteEntity;
import com.viesant.LabMedical.entities.UsuarioEntity;

import java.util.List;

public class PacienteMapper {

  public static PacienteEntity map(PacienteRequest source) {
    PacienteEntity target = new PacienteEntity();

    // Mapeia os dados pessoais
    PacienteEntity.DadosPessoais dadosPessoais = new PacienteEntity.DadosPessoais();
    dadosPessoais.setNome(source.dadosPessoais().nome());
    dadosPessoais.setGenero(source.dadosPessoais().genero());
    dadosPessoais.setDataNascimento(source.dadosPessoais().dataNascimento());
    dadosPessoais.setCpf(source.dadosPessoais().cpf());
    dadosPessoais.setRg(source.dadosPessoais().rg());
    dadosPessoais.setEstadoCivil(source.dadosPessoais().estadoCivil());
    dadosPessoais.setTelefone(source.dadosPessoais().telefone());
    dadosPessoais.setEmail(source.dadosPessoais().email());
    dadosPessoais.setNaturalidade(source.dadosPessoais().naturalidade());
    target.setDadosPessoais(dadosPessoais);

    // Mapeia os dados de saúde
    PacienteEntity.Saude saude = new PacienteEntity.Saude();
    saude.setTelefoneContato(source.saude().telefoneContato());
    saude.setAlergias(source.saude().alergias() != null ? source.saude().alergias() : List.of());
    saude.setCuidados(source.saude().cuidados() != null ? source.saude().cuidados() : List.of());
    saude.setNomeConvenio(source.saude().nomeConvenio());
    saude.setNumeroConvenio(source.saude().numeroConvenio());
    saude.setValidadeConvenio(source.saude().validadeConvenio());
    target.setSaude(saude);

    // Mapeia o endereço
    PacienteEntity.Endereco endereco = new PacienteEntity.Endereco();
    endereco.setCep(source.endereco().cep());
    endereco.setCidade(source.endereco().cidade());
    endereco.setEstado(source.endereco().estado());
    endereco.setLogradouro(source.endereco().logradouro());
    endereco.setNumero(source.endereco().numero());
    endereco.setComplemento(source.endereco().complemento());
    endereco.setBairro(source.endereco().bairro());
    endereco.setPontoReferencia(source.endereco().pontoReferencia());
    target.setEndereco(endereco);

    // Associa o usuário
//    UsuarioEntity usuario = ;
//    target.setUsuario(usuario);

    return target;
  }
}
