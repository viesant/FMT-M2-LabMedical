package com.viesant.LabMedical.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

public record ExameRequest(
        @NotBlank(message = "O nome do exame é obrigatório.")
        @Size(min = 8, max = 64, message = "O nome do exame deve ter entre 8 e 64 caracteres.")
        String nomeExame,

        @NotNull(message = "A data do exame é obrigatória.")
        LocalDate dataExame,

        @NotNull(message = "O horário do exame é obrigatório.")
        LocalTime horarioExame,

        @NotBlank(message = "O tipo do exame é obrigatório.")
        @Size(min = 4, max = 32, message = "O tipo do exame deve ter entre 4 e 32 caracteres.")
        String tipoExame,

        @NotBlank(message = "O laboratório é obrigatório.")
        @Size(min = 4, max = 32, message = "O laboratório deve ter entre 4 e 32 caracteres.")
        String laboratorio,

        @Size(min = 16, max = 1024, message = "Os resultados devem ter entre 16 e 1024 caracteres.")
        String resultados,

        @Size(max = 1024, message = "A URL do documento deve ter no máximo 1024 caracteres.")
        String urlDocumento,

        @NotNull(message = "O ID do paciente é obrigatório.")
        Long pacienteId
) {}