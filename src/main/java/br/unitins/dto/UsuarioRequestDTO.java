package br.unitins.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDTO(
        @NotBlank(message = "O login do usuário é obrigatório.") String login,
        @NotBlank(message = "O nome do usuário é obrigatório.") String nome,
        String email,
        @NotNull(message = "O perfil do usuário é obrigatório.") Long perfil,
        @NotNull(message = "O campo ativo é obrigatório.") Boolean ativo,
        String cpf,
        String telefone,
        @NotBlank(message = "A senha do usuário é obrigatória.") String senha
        ) {

}
