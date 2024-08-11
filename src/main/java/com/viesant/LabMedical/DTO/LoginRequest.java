package com.viesant.LabMedical.DTO;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @NotEmpty(message = "Usuário é obrigatório") String usuario,
        @NotEmpty(message = "Senha é obrigatória") String senha
) {}
