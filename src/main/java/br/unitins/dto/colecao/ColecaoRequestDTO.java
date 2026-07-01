package br.unitins.dto.colecao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ColecaoRequestDTO(
        @NotBlank(message = "O nome da coleção é obrigatório.") String nome,

        @NotBlank(message = "A descrição da coleção é obrigatória.") String descricao,

        @NotNull(message = "A data de início da publicação é obrigatória.") LocalDate dataInicioPublicacao,

        @NotNull(message = "A data de fim da publicação é obrigatória.") LocalDate dataFimPublicacao) {
}