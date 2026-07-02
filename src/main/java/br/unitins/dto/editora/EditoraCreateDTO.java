package br.unitins.dto.editora;

import jakarta.validation.constraints.NotBlank;

public record EditoraRequestDTO(
        @NotBlank(message = "O nome da editora é obrigatório.") String nome,
        @NotBlank(message = "O CNPJ da editora é obrigatório.") String cnpj) {
}