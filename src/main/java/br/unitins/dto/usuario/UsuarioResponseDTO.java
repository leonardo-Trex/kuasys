package br.unitins.dto.usuario;

public record UsuarioResponseDTO(
        Long id,
        String login,
        String nome,
        String email,
        Boolean ativo,
        String cpf,
        String telefone) {
}
