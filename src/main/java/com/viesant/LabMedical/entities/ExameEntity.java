package com.viesant.LabMedical.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "exame")
public class ExameEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 64)
  private String nomeExame;

  @Column(nullable = false)
  private LocalDate dataExame;

  @Column(nullable = false)
  private LocalTime horarioExame;

  @Column(nullable = false, length = 32)
  private String tipoExame;

  @Column(nullable = false, length = 32)
  private String laboratorio;

  @Column(length = 1024)
  private String resultados;

  @Column(length = 1024)
  private String urlDocumento;

  @ManyToOne
  @JoinColumn(name = "paciente_id", nullable = false)
  private PacienteEntity paciente;
}
