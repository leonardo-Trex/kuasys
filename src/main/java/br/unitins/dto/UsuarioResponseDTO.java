package br.unitins.dto;

import br.unitins.model.enums.Perfil;

public record UsuarioResponseDTO(
        Long id,
        String login,
        String nome,
        String email,
        Perfil perfil,
        Boolean ativo) {

}
