package com.viesant.LabMedical.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

public record ConsultaRequest(
    @NotBlank(message = "O motivo da consulta é obrigatório.")
        @Size(min = 8, max = 64, message = "O motivo da consulta deve ter entre 8 e 64 caracteres.")
        String motivoConsulta,
    @NotNull(message = "A data da consulta é obrigatória.") LocalDate dataConsulta,
    @NotNull(message = "O horário da consulta é obrigatório.") LocalTime horarioConsulta,
    @NotBlank(message = "A descrição do problema é obrigatória.")
        @Size(
            min = 16,
            max = 1024,
            message = "A descrição do problema deve ter entre 16 e 1024 caracteres.")
        String descricaoProblema,
    String medicacaoReceitada,
    @Size(
            min = 16,
            max = 256,
            message = "A dosagem e precauções deve ter entre 16 e 256 caracteres.")
        String dosagemPrecaucoes,
    @NotNull(message = "Os dados do paciente são obrigatórios.") long pacienteId) {}
