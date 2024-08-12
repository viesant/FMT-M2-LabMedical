package com.viesant.LabMedical.controllers;

import com.viesant.LabMedical.DTO.ConsultaRequest;
import com.viesant.LabMedical.entities.ConsultaEntity;
import com.viesant.LabMedical.services.ConsultaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/consultas")
public class ConsultaController {
  private final ConsultaService consultaService;

  @PostMapping
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_MEDICO')")
  public ResponseEntity<ConsultaEntity> novoConsulta(
      @Valid @RequestBody ConsultaRequest consultaRequest) {

    return new ResponseEntity<>(consultaService.novoConsulta(consultaRequest), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ConsultaEntity> buscaConsultaPorId(
      @PathVariable Long id, JwtAuthenticationToken token) {
    return ResponseEntity.ok(consultaService.buscaConsultaPorId(id, token));
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_MEDICO')")
  public ResponseEntity<ConsultaEntity> editaConsultaPorId(
      @PathVariable Long id, @Valid @RequestBody ConsultaRequest consultaRequest) {
    return ResponseEntity.ok(consultaService.editaConsultaPorId(id, consultaRequest));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_MEDICO')")
  public ResponseEntity<Void> deletaConsultaPorId(@PathVariable Long id) {
    consultaService.deletaConsultaPorId(id);
    return ResponseEntity.noContent().build();
  }
}
