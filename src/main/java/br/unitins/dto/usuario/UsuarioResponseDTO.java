package br.unitins.dto.usuario;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        Boolean ativo,
        String cpf,
        String telefone) {
}
