package br.unitins.Service.interfaces;

import java.util.List;

import br.unitins.model.Usuario;

public interface UsuarioService {
    List<Usuario> findAll();

    Usuario findById(Long id);

    List<Usuario> findByNome(String nome);

    Usuario findByLogin(String login);

    Usuario findByEmail(String email);

    Usuario create(Usuario usuario);

    void update(Long id, Usuario usuario);

    void delete(Long id);

    // DTO-based helper methods
    br.unitins.dto.UsuarioResponseDTO criar(br.unitins.dto.UsuarioRequestDTO dto);

    br.unitins.dto.UsuarioResponseDTO atualizarDto(Long id, br.unitins.dto.UsuarioRequestDTO dto);

    br.unitins.dto.UsuarioResponseDTO buscarPorKeycloakId(String keycloakId);

    boolean deletarPorId(Long id);
}
