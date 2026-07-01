package br.unitins.service;

import br.unitins.dto.endereco.EnderecoRequestDTO;
import br.unitins.dto.endereco.EnderecoResponseDTO;
import br.unitins.mapper.EnderecoMapper;
import br.unitins.model.Endereco;
import br.unitins.model.Usuario;
import br.unitins.repository.EnderecoRepository;
import br.unitins.repository.UsuarioRepository;
import br.unitins.service.interfaces.EnderecoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {

    @Inject
    EnderecoRepository repository;

    @Inject
    UsuarioRepository usuarioRepository;

    /**
     * Busca o usuário local a partir do keycloakId (UUID vindo do JWT).
     * Padrão idêntico ao usado em WishlistServiceImpl.
     */
    private Usuario findUsuarioByKeycloakId(String keycloakId) {
        Usuario usuario = usuarioRepository.findByKeycloakId(keycloakId);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado para o keycloakId informado.");
        }
        return usuario;
    }

    @Override
    public List<EnderecoResponseDTO> listarEnderecosDoCliente(String keycloakId) {
        Usuario usuario = findUsuarioByKeycloakId(keycloakId);
        List<Endereco> enderecos = repository.findByUsuarioId(usuario.getId());
        return enderecos.stream()
                .map(EnderecoMapper::toResponseDTO)
                .toList();
    }

    @Override
    public EnderecoResponseDTO buscarPorIdECliente(Long id, String keycloakId) {
        Usuario usuario = findUsuarioByKeycloakId(keycloakId);
        Endereco endereco = repository.findById(id);

        if (endereco == null) {
            throw new NotFoundException("Endereço não encontrado");
        }

        if (!endereco.getUsuario().getId().equals(usuario.getId())) {
            throw new NotFoundException("Endereço não pertence ao usuário");
        }

        return EnderecoMapper.toResponseDTO(endereco);
    }

    @Override
    @Transactional
    public EnderecoResponseDTO salvar(String keycloakId, EnderecoRequestDTO dto) {
        Usuario usuario = findUsuarioByKeycloakId(keycloakId);

        // Converte o DTO para entidade, associando ao usuário autenticado
        Endereco endereco = EnderecoMapper.toEntity(dto, usuario);

        // Sincroniza o lado bidirecional
        usuario.getEnderecos().add(endereco);

        repository.persist(endereco);

        return EnderecoMapper.toResponseDTO(endereco);
    }
}
