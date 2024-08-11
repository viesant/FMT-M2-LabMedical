package com.viesant.LabMedical.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "paciente")
public class PacienteEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 64)
  private String nome;

  @Column(nullable = false)
  private String genero;

  @Column(nullable = false)
  private LocalDate dataNascimento;

  @Column(nullable = false, unique = true, length = 14)
  private String cpf;

  @Column(nullable = false, length = 20)
  private String rg;

  @Column(nullable = false)
  private String estadoCivil;

  @Column(nullable = false)
  private String telefone;

  private String email;
  @Column(nullable = false, length = 64)
  private String naturalidade;

  @Column(nullable = false)
  private String telefoneContato;

  private List<String> alergias;

  private List<String> cuidados;

  private String nomeConvenio;

  private String numeroConvenio;

  private LocalDate validadeConvenio;

  @Embedded private EnderecoEmbEntity endereco;

  @OneToOne
  @JoinColumn(name = "usuario_id", nullable = false)
  private UsuarioEntity usuario;

  /*
  Nome Completo: Obrigatório, com máximo e mínimo de 64 e 8 caracteres, respectivamente.
  Gênero: Obrigatório.
  Data de Nascimento: Obrigatório, data válida.
  CPF: Obrigatório com o formato 000.000.000-00
  RG com órgão expedidor: Obrigatório, com máximo de 20 caracteres.
  Estado Civil: Obrigatório.
  Telefone: Obrigatório com o formato (99) 9 9999-9999
  E-mail: Não obrigatório e com validação de formato.
  Naturalidade: Obrigatório, com máximo e mínimo de 64 e 8 caracteres, respectivamente.
  Contato de Emergência: Obrigatório com o formato (99) 9 9999-9999
  Lista de Alergias: Não obrigatório.
  Lista de Cuidados Específicos: Não obrigatório.
  Convênio: Não obrigatório.
  Número do Convênio: Não obrigatório.
  Validade do Convênio: Não obrigatório.
  Endereço: Cep, Cidade, Estado, Logradouro, Número, Complemento, Bairro e Ponto de Referência.

     */
}
