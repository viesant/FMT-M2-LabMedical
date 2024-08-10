package com.viesant.LabMedical.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usuario")
public class UsuarioEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String nome;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private LocalDate dataNascimento;

  @Column(nullable = false, length = 14, unique = true)
  private String cpf;

  @Column(nullable = false)
  private String password;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "usuario_perfil",
          joinColumns = @JoinColumn(name = "usuario_id"),
          inverseJoinColumns = @JoinColumn(name = "perfil_id"))
  private Set<PerfilEntity> perfil;
}
