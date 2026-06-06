package br.unitins.service;

import java.util.List;

import br.unitins.service.interfaces.WishlistService;
import br.unitins.model.Edicao;
import br.unitins.model.Usuario;
import br.unitins.repository.EdicaoRepository;
import br.unitins.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class WishlistServiceImpl implements WishlistService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    EdicaoRepository edicaoRepository;

    private Usuario findUsuarioByKeycloakId(String keycloakId) {
        Usuario usuario = usuarioRepository.findByKeycloakId(keycloakId);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }
        return usuario;
    }

    @Override
    public List<Edicao> listar(String keycloakId) {
        Usuario usuario = findUsuarioByKeycloakId(keycloakId);
        return usuario.getWishlist();
    }

    @Override
    @Transactional
    public void adicionar(String keycloakId, Long edicaoId) {
        Usuario usuario = findUsuarioByKeycloakId(keycloakId);
        Edicao edicao = edicaoRepository.findById(edicaoId);
        if (edicao == null) {
            throw new NotFoundException("Edição não encontrada.");
        }

        // Evita duplicatas
        boolean jaExiste = usuario.getWishlist().stream()
                .anyMatch(e -> e.getId().equals(edicaoId));
        if (!jaExiste) {
            usuario.getWishlist().add(edicao);
        }
    }

    @Override
    @Transactional
    public void remover(String keycloakId, Long edicaoId) {
        Usuario usuario = findUsuarioByKeycloakId(keycloakId);

        boolean removido = usuario.getWishlist().removeIf(e -> e.getId().equals(edicaoId));
        if (!removido) {
            throw new NotFoundException("Edição não encontrada na lista de desejos.");
        }
    }
}
