package com.viesant.LabMedical.controllers;

import com.viesant.LabMedical.DTO.UsuarioRequest;
import com.viesant.LabMedical.DTO.UsuarioResponse;
import com.viesant.LabMedical.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
public class UsuarioController {
  private final UsuarioService usuarioService;

  @PostMapping("/usuarios")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
  public ResponseEntity<UsuarioResponse> novoUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest) {
    return new ResponseEntity<>(usuarioService.novoUsuario(usuarioRequest),HttpStatus.CREATED);
  }
}
