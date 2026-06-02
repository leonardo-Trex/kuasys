package br.unitins.dto;

import br.unitins.model.enums.Perfil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDTO(
        @NotBlank(message = "O login do usuário é obrigatório.") String login,
        @NotBlank(message = "A senha do usuário é obrigatória.") String senha,
        @NotBlank(message = "O nome do usuário é obrigatório.") String nome,
        String email,
        @NotNull(message = "O perfil do usuário é obrigatório.") Perfil perfil,
        @NotNull(message = "O campo ativo é obrigatório.") Boolean ativo,
        String cpf,
        String telefone,
        String keycloakId) {

}
