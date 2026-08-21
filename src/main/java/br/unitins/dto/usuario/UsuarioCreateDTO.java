package br.unitins.dto.usuario;

public record UsuarioCreateDTO(
//      TODO: Evoluir para validações e a lista de Perfis
        String nome,
        String email,
        String cpf,
        String telefone,
        String senha
) {

}
