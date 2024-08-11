package com.viesant.LabMedical.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record UsuarioRequest(
    @NotEmpty(message = "Nome é obrigatório")
        @Size(max = 255, message = "Nome deve ter no máximo 255 caracteres")
        String nome,
    @NotEmpty(message = "Email é obrigatório")
        @Email(message = "Email deve ser válido")
        @Size(max = 255, message = "Email deve ter no máximo 255 caracteres")
        String email,
    @NotNull(message = "Data de nascimento é obrigatória") LocalDate dataNascimento,
    @NotEmpty(message = "CPF é obrigatório")
        @Size(max = 14, message = "CPF deve ter no máximo 14 caracteres")
        String cpf,
    @NotEmpty(message = "Senha é obrigatória")
        @Size(max = 255, message = "Senha deve ter no máximo 255 caracteres")
        String password,
    @NotNull(message = "Perfil é obrigatório") String perfil) {}
