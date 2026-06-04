package br.unitins.mapper;

import br.unitins.dto.UsuarioRequestDTO;
import br.unitins.dto.UsuarioResponseDTO;
import br.unitins.model.Usuario;
import br.unitins.model.enums.Perfil;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        // Map to the current Usuario entity which stores Keycloak ID and profile data
        Usuario usuario = new Usuario(
                null,
                null,
                dto.nome(),
                dto.email(),
                dto.cpf(),
                dto.telefone(),
                dto.login(),
                Perfil.valueOf(dto.perfil()),
                dto.ativo()
        );
        usuario.setSenha(dto.senha());
        return usuario;
    }

    public static UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        // Some legacy fields in DTO may not exist on the current entity; set them to null/defaults
        return new UsuarioResponseDTO(
                usuario.getId(),
                null,
                usuario.getNome(),
                usuario.getEmail(),
                null,
                null,
                usuario.getCpf(),
                usuario.getTelefone(),
                usuario.getKeycloakId()
        );
    }
}
