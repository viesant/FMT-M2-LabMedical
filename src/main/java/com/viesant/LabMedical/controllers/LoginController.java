package com.viesant.LabMedical.controllers;


import com.viesant.LabMedical.DTO.LoginRequest;
import com.viesant.LabMedical.DTO.LoginResponse;
import com.viesant.LabMedical.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController {
  private final LoginService loginService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok(loginService.login(loginRequest));
  }
}
