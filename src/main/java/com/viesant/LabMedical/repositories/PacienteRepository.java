package com.viesant.LabMedical.repositories;

import com.viesant.LabMedical.entities.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {}
