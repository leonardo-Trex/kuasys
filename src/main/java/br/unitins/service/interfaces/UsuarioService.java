package br.unitins.service.interfaces;

import br.unitins.dto.usuario.UsuarioCreateDTO;
import br.unitins.dto.usuario.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioResponseDTO create(UsuarioCreateDTO dto);

    UsuarioResponseDTO update(Long id, UsuarioCreateDTO dto);

    List<UsuarioResponseDTO> findAll();

    UsuarioResponseDTO findById(Long id);

    List<UsuarioResponseDTO> findByNome(String nome);

    UsuarioResponseDTO findByLogin(String login);

    UsuarioResponseDTO findByEmail(String email);

    void delete(Long id);

//    TODO: Necessário só no futuro.
//    UsuarioResponseDTO findByKeycloakId(String keycloakId);

}
