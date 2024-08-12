package com.viesant.LabMedical.controllers;

import com.viesant.LabMedical.DTO.ExameRequest;
import com.viesant.LabMedical.entities.ExameEntity;
import com.viesant.LabMedical.services.ExameService;
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
@RequestMapping("/exames")
public class ExameController {
  private final ExameService exameService;

  @PostMapping
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_MEDICO')")
  public ResponseEntity<ExameEntity> novoExame(
      @Valid @RequestBody ExameRequest exameRequest) {

    return new ResponseEntity<>(exameService.novoExame(exameRequest), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ExameEntity> buscaExamePorId(
      @PathVariable Long id, JwtAuthenticationToken token) {
    return ResponseEntity.ok(exameService.buscaExamePorId(id, token));
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_MEDICO')")
  public ResponseEntity<ExameEntity> editaExamePorId(
      @PathVariable Long id, @Valid @RequestBody ExameRequest exameRequest) {
    return ResponseEntity.ok(exameService.editaExamePorId(id, exameRequest));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN','SCOPE_MEDICO')")
  public ResponseEntity<Void> deletaExamePorId(@PathVariable Long id) {
    exameService.deletaExamePorId(id);
    return ResponseEntity.noContent().build();
  }
}
