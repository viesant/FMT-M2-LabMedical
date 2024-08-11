package com.viesant.LabMedical.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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

  @Embedded DadosPessoais dadosPessoais;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Embedded private Saude saude;

  @Embedded private Endereco endereco;

  @OneToOne
  @JoinColumn(name = "usuario_id", nullable = false)
  private UsuarioEntity usuario;

  @Embeddable
  @Getter
  @Setter
  public static class DadosPessoais {
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
  }

  @Embeddable
  @Getter
  @Setter
  public static class Saude {
    @Column(nullable = false)
    private String telefoneContato;

    private List<String> alergias;

    private List<String> cuidados;

    private String nomeConvenio;

    private String numeroConvenio;

    private LocalDate validadeConvenio;
  }

  @Embeddable
  @Getter
  @Setter
  public static class Endereco {

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String numero;

    private String complemento;

    @Column(nullable = false)
    private String bairro;

    private String pontoReferencia;
  }
}
