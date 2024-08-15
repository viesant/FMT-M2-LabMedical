package com.viesant.LabMedical.services;

import com.viesant.LabMedical.DTO.DashboardResponse;
import com.viesant.LabMedical.repositories.ConsultaRepository;
import com.viesant.LabMedical.repositories.ExameRepository;
import com.viesant.LabMedical.repositories.PacienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DashboardService {
  private final PacienteRepository pacienteRepository;
  private final ConsultaRepository consultaRepository;
  private final ExameRepository exameRepository;

  public DashboardResponse dashboard() {
    return new DashboardResponse(
        pacienteRepository.count(), consultaRepository.count(), exameRepository.count());
  }
}
