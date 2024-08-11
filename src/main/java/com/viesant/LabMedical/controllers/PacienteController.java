package com.viesant.LabMedical.controllers;

import com.viesant.LabMedical.DTO.PacienteRequest;
import com.viesant.LabMedical.entities.PacienteEntity;
import com.viesant.LabMedical.services.PacienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
