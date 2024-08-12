package com.viesant.LabMedical.repositories;

import com.viesant.LabMedical.entities.ConsultaEntity;
import com.viesant.LabMedical.entities.ExameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExameRepository extends JpaRepository<ExameEntity, Long> {}
