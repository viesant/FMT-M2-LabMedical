package com.viesant.LabMedical.DTO;

public record DashboardResponse(
        Long contaPacientes,
        Long contaConsulta,
        Long contaExames
) {}
