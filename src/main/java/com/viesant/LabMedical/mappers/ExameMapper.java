package com.viesant.LabMedical.mappers;

import com.viesant.LabMedical.DTO.ExameRequest;
import com.viesant.LabMedical.entities.ExameEntity;
import com.viesant.LabMedical.entities.PacienteEntity;

public class ExameMapper {
  public static ExameEntity map(ExameRequest source, PacienteEntity paciente) {
    ExameEntity target = new ExameEntity();

    target.setNomeExame(source.nomeExame());
    target.setDataExame(source.dataExame());
    target.setHorarioExame(source.horarioExame());
    target.setTipoExame(source.tipoExame());
    target.setLaboratorio(source.laboratorio());
    target.setResultados(source.resultados());
    target.setUrlDocumento(source.urlDocumento());
    target.setPaciente(paciente);

    return target;
  }
}
