package com.viesant.LabMedical.services;

import com.viesant.LabMedical.repositories.PacienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PacienteService {
  private final PacienteRepository pacienteRepository;



}
