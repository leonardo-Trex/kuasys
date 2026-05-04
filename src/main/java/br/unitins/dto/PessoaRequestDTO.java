package br.unitins.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PessoaRequestDTO(
        @NotBlank(message = "O nome da pessoa é obrigatório.") String nome,
        @NotBlank(message = "A nacionalidade da pessoa é obrigatória.") String nacionalidade,
        @NotNull(message = "A data de nascimento é obrigatória.") LocalDate dataNascimento) {

}
