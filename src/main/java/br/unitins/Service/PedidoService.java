package br.unitins.Service;

import br.unitins.exceptions.ValidationException;
import br.unitins.model.Carrinho;
import br.unitins.model.ItemCarrinho;
import br.unitins.model.ItemPedido;
import br.unitins.model.Pedido;
import br.unitins.model.enums.StatusPedido;
import br.unitins.repository.CarrinhoRepository;
import br.unitins.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApplicationScoped
public class PedidoService {

    @Inject
    CarrinhoRepository carrinhoRepository;

    @Inject
    PedidoRepository pedidoRepository;

    @Transactional
    public Pedido criarPedidoAPartirDoCarrinho(String tokenSessao, String usuarioId) {
        Carrinho carrinho = locateActiveCart(tokenSessao, usuarioId);

        if (carrinho == null || carrinho.getItens() == null || carrinho.getItens().isEmpty()) {
            throw new ValidationException("Carrinho não encontrado ou está vazio");
        }

        Pedido pedido = new Pedido();
        pedido.setUsuarioId(usuarioId);
        pedido.setTokenSessao(usuarioId == null ? tokenSessao : null);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatusPedido(StatusPedido.AGUARDANDO_PAGAMENTO);

        BigDecimal valorTotal = BigDecimal.ZERO;
        BigDecimal precoPadrao = new BigDecimal("10.00");

        for (ItemCarrinho itemCarrinho : carrinho.getItens()) {
            BigDecimal itemTotal = precoPadrao.multiply(BigDecimal.valueOf(itemCarrinho.getQuantidade()));
            valorTotal = valorTotal.add(itemTotal);

            ItemPedido itemPedido = new ItemPedido(
                    itemCarrinho.getProdutoId(),
                    itemCarrinho.getQuantidade(),
                    precoPadrao,
                    pedido
            );
            pedido.addItem(itemPedido);
        }

        pedido.setValorTotal(valorTotal);
        pedidoRepository.persist(pedido);

        carrinhoRepository.delete(carrinho);

        return pedido;
    }

    private Carrinho locateActiveCart(String tokenSessao, String usuarioId) {
        if (usuarioId != null && !usuarioId.isBlank()) {
            return carrinhoRepository.findByUsuarioId(usuarioId);
        }
        if (tokenSessao != null && !tokenSessao.isBlank()) {
            return carrinhoRepository.findByTokenSessao(tokenSessao);
        }
        return null;
    }
}
