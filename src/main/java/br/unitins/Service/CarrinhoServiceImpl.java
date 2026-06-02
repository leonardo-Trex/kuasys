package br.unitins.Service;

import br.unitins.Service.interfaces.CarrinhoService;
import br.unitins.Service.interfaces.EdicaoService;
import br.unitins.dto.CarrinhoRequestDTO;
import br.unitins.model.Carrinho;
import br.unitins.model.Edicao;
import br.unitins.model.ItemCarrinho;
import br.unitins.repository.CarrinhoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class CarrinhoServiceImpl implements CarrinhoService {

    @Inject
    CarrinhoRepository carrinhoRepository;
    
    @Inject
    EdicaoService edicaoService;

    @Override
    @Transactional
    public Carrinho adicionarItem(CarrinhoRequestDTO dto, String cartToken, String usuarioId) {
        Carrinho carrinho = locateCart(cartToken, usuarioId);
        Edicao e = edicaoService.findById(Long.parseLong(dto.produtoId()));

        if (carrinho == null) {
            if (usuarioId != null && !usuarioId.isBlank()) {
                carrinho = new Carrinho(usuarioId, null);
            } else {
                cartToken = UUID.randomUUID().toString();
                carrinho = new Carrinho(null, cartToken);
            }
        }

        Optional<ItemCarrinho> itemExistente = carrinho.getItens().stream()
                .filter(item -> item.getEdicao().getId().equals(e.getId()))
                .findFirst();

        if (itemExistente.isPresent()) {
            ItemCarrinho item = itemExistente.get();
            item.setQuantidade(item.getQuantidade() + dto.quantidade());
        } else {
            ItemCarrinho novoItem = new ItemCarrinho(
                    e,
                    dto.quantidade(),
                    e.getPreco(),
                    carrinho
            );
            carrinho.getItens().add(novoItem);
        }

        carrinhoRepository.persist(carrinho);
        return carrinho;
    }

    @Override
    public Carrinho obterCarrinho(String cartToken, String usuarioId) {
        return locateCart(cartToken, usuarioId);
    }

    @Override
    @Transactional
    public boolean removerItem(Carrinho carrinho, Long itemId) {
        boolean removed = carrinho.getItens().removeIf(item -> item.getId().equals(itemId));
        if (removed) {
            carrinhoRepository.persist(carrinho);
        }
        return removed;
    }

    @Override
    @Transactional
    public Carrinho vincularCarrinho(String tokenSessao, String usuarioId) {
        Carrinho carrinhoAnonimo = carrinhoRepository.findByTokenSessao(tokenSessao);
        if (carrinhoAnonimo == null) {
            return null;
        }

        if (carrinhoAnonimo.getUsuarioId() != null) {
            throw new IllegalStateException("Este carrinho já está vinculado a um usuário");
        }

        Carrinho carrinhoUsuario = carrinhoRepository.findByUsuarioId(usuarioId);

        if (carrinhoUsuario == null) {
            carrinhoAnonimo.setUsuarioId(usuarioId);
            carrinhoAnonimo.setTokenSessao(null);
            carrinhoRepository.persist(carrinhoAnonimo);
            return carrinhoAnonimo;
        }

        for (ItemCarrinho itemAnonimo : carrinhoAnonimo.getItens()) {
            Optional<ItemCarrinho> itemExistente = carrinhoUsuario.getItens().stream()
                    .filter(item -> item.getEdicao().getId().equals(itemAnonimo.getEdicao().getId()))
                    .findFirst();

            if (itemExistente.isPresent()) {
                ItemCarrinho item = itemExistente.get();
                item.setQuantidade(item.getQuantidade() + itemAnonimo.getQuantidade());
            } else {
                ItemCarrinho novoItem = new ItemCarrinho(
                        itemAnonimo.getEdicao(),
                        itemAnonimo.getQuantidade(),
                        itemAnonimo.getPrecoUnitario(),
                        carrinhoUsuario
                );
                carrinhoUsuario.getItens().add(novoItem);
            }
        }

        carrinhoRepository.persist(carrinhoUsuario);
        carrinhoRepository.delete(carrinhoAnonimo);

        return carrinhoUsuario;
    }

    private Carrinho locateCart(String cartToken, String usuarioId) {
        if (usuarioId != null && !usuarioId.isBlank()) {
            return carrinhoRepository.findByUsuarioId(usuarioId);
        }
        if (cartToken != null && !cartToken.isBlank()) {
            return carrinhoRepository.findByTokenSessao(cartToken);
        }
        return null;
    }
}
