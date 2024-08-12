package com.viesant.LabMedical.DTO;

public record PacienteGetRequest(
        String nome,
        String telefone,
        String email
) {}
