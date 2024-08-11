package com.viesant.LabMedical.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class EnderecoEmbEntity {

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
