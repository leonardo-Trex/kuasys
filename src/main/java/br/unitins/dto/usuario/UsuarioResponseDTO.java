package br.unitins.dto.usuario;

import br.unitins.model.enums.Perfil;

public record UsuarioResponseDTO(
        Long id,
        String login,
        String nome,
        String email,
        Long perfilId,
        Perfil perfil,
        Boolean ativo,
        String cpf,
        String telefone) {
}
