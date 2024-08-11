package com.viesant.LabMedical.DTO;

public record PacienteResponse(
        String nome,
        Integer idade,
        String contato,
        String planoSaude
) {}


