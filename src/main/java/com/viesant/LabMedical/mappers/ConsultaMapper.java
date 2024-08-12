package com.viesant.LabMedical.mappers;

import com.viesant.LabMedical.DTO.ConsultaRequest;
import com.viesant.LabMedical.entities.ConsultaEntity;
import com.viesant.LabMedical.entities.PacienteEntity;

public class ConsultaMapper {
  public static ConsultaEntity map(ConsultaRequest source, PacienteEntity paciente) {
    ConsultaEntity target = new ConsultaEntity();

    target.setMotivoConsulta(source.motivoConsulta());
    target.setDataConsulta(source.dataConsulta());
    target.setHorarioConsulta(source.horarioConsulta());
    target.setDescricaoProblema(source.descricaoProblema());
    target.setMedicacaoReceitada(source.medicacaoReceitada());
    target.setDosagemPrecaucoes(source.dosagemPrecaucoes());

    target.setPaciente(paciente);

    return target;
  }
}
