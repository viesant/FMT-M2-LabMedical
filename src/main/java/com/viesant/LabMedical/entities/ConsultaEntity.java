package com.viesant.LabMedical.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "consulta")
public class ConsultaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 64, nullable = false)
  private String motivoConsulta;

  @Column(nullable = false)
  private LocalDate dataConsulta;

  @Column(nullable = false)
  private LocalTime horarioConsulta;

  @Column(length = 1024, nullable = false)
  private String descricaoProblema;

  private String medicacaoReceitada;

  private String dosagemPrecaucoes;

  @ManyToOne
  @JoinColumn(name = "paciente_id", nullable = false)
  private PacienteEntity paciente;
}
