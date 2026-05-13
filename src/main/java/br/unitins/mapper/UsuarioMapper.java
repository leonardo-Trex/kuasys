package br.unitins.mapper;

import br.unitins.dto.UsuarioRequestDTO;
import br.unitins.dto.UsuarioResponseDTO;
import br.unitins.model.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setLogin(dto.login());
        usuario.setSenhaHash(dto.senha());
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setPerfil(dto.perfil());
        usuario.setAtivo(dto.ativo());

        return usuario;
    }

    public static UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil(),
                usuario.getAtivo());
    }
}
