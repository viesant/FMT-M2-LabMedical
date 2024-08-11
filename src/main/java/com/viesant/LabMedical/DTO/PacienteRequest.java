package com.viesant.LabMedical.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public record PacienteRequest(
        @Valid DadosPessoais dadosPessoais,
        @Valid Saude saude,
        @Valid Endereco endereco,

        @NotNull Long usuarioId
) {
        public record DadosPessoais(
                @NotEmpty(message = "Nome é obrigatório")
                @Size(max = 64, message = "Nome deve ter no máximo 64 caracteres")
                String nome,

                @NotEmpty(message = "Gênero é obrigatório")
                String genero,

                @NotNull(message = "Data de nascimento é obrigatória")
                LocalDate dataNascimento,

                @NotEmpty(message = "CPF é obrigatório")
                @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve estar no formato 000.000.000-00")
                String cpf,

                @NotEmpty(message = "RG é obrigatório")
                @Size(max = 20, message = "RG deve ter no máximo 20 caracteres")
                String rg,

                @NotEmpty(message = "Estado civil é obrigatório")
                String estadoCivil,

                @NotEmpty(message = "Telefone é obrigatório")
                @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "Telefone deve estar no formato (99) 99999-9999")
                String telefone,

                @Email(message = "Email deve ser válido")
                String email,

                @NotEmpty(message = "Naturalidade é obrigatória")
                @Size(min = 8, max = 64, message = "Naturalidade deve ter entre 8 e 64 caracteres")
                String naturalidade
        ) {}

        public record Saude(
                @NotEmpty(message = "Telefone de contato é obrigatório")
                @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "Telefone de contato deve estar no formato (99) 99999-9999")
                String telefoneContato,

                List<String> alergias,

                List<String> cuidados,

                String nomeConvenio,

                String numeroConvenio,

                LocalDate validadeConvenio
        ) {}

        public record Endereco(
                @NotEmpty(message = "CEP é obrigatório")
                String cep,

                @NotEmpty(message = "Cidade é obrigatória")
                String cidade,

                @NotEmpty(message = "Estado é obrigatório")
                String estado,

                @NotEmpty(message = "Logradouro é obrigatório")
                String logradouro,

                @NotEmpty(message = "Número é obrigatório")
                String numero,

                String complemento,

                @NotEmpty(message = "Bairro é obrigatório")
                String bairro,

                String pontoReferencia
        ) {}
}
