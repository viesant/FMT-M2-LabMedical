package com.viesant.LabMedical.DTO;

import java.time.LocalDate;
import java.util.Set;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        String password,
        LocalDate dataNascimento,
        String cpf,
        String perfil
) {}
