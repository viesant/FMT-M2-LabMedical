package com.viesant.LabMedical.controllers;

import com.viesant.LabMedical.DTO.PacienteGetRequest;
import com.viesant.LabMedical.DTO.PacienteRequest;
import com.viesant.LabMedical.DTO.PacienteResponse;
import com.viesant.LabMedical.entities.PacienteEntity;
import com.viesant.LabMedical.services.PacienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/pacientes")
public class PacienteController {
  private final PacienteService pacienteService;

  @PostMapping
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_MEDICO')")
  public ResponseEntity<PacienteEntity> novoPaciente(
      @Valid @RequestBody PacienteRequest pacienteRequest) {

    return new ResponseEntity<>(pacienteService.novoPaciente(pacienteRequest), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PacienteEntity> buscaPacientePorId(@PathVariable Long id, JwtAuthenticationToken token) {
    return ResponseEntity.ok(pacienteService.buscaPacientePorId(id, token));
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_MEDICO')")
  public ResponseEntity<PacienteEntity> editaPacientePorId(@PathVariable Long id,
                                                           @Valid @RequestBody PacienteRequest pacienteRequest){
    return ResponseEntity.ok(pacienteService.editaPacientePorId(id, pacienteRequest));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_MEDICO')")
  public ResponseEntity<Void> deletaPacientePorId(@PathVariable Long id
                                                           ){
    pacienteService.deletaPacientePorId(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_MEDICO')")
//  public Pageable listaPaginada(@PageableDefault(size = 10) Pageable paginacao){
//    return paginacao;
//  }   @PageableDefault(size = 10)
  public Page<PacienteResponse> listaPaginada( Pageable paginacao){
    return pacienteService.listaPaginada(paginacao);
  }

//  @GetMapping
//  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_MEDICO')")
//  public ResponseEntity<Page<PacienteResponse>> listaPacientes(
//          PacienteGetRequest filtro,
//          @PageableDefault(size = 10) Pageable paginacao) {
//
//    Page<PacienteResponse> pacientes = pacienteService.listaPacientes(filtro, paginacao);
//    return ResponseEntity.ok(pacientes);
//  }
}
