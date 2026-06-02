package br.unitins.Service;

import br.unitins.Service.interfaces.PedidoService;
import br.unitins.dto.ItemPedidoDTO;
import br.unitins.dto.PedidoRequestDTO;
import br.unitins.dto.PedidoResponseDTO;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    CarrinhoRepository carrinhoRepository;

    @Override
    @Transactional
    public PedidoResponseDTO finalizarCompra(PedidoRequestDTO dto) {
        // Buscar o carrinho ativo
        Carrinho carrinho = locateCart(dto.cartToken(), dto.usuarioId());

        if (carrinho == null) {
            throw new ValidationException("Carrinho não encontrado. Por favor, adicione itens ao carrinho antes de finalizar a compra.");
        }

        if (carrinho.getItens() == null || carrinho.getItens().isEmpty()) {
            throw new ValidationException("Carrinho vazio. Adicione itens antes de finalizar a compra.");
        }

        // Calcular valor total
        BigDecimal valorTotal = carrinho.getItens().stream()
                .map(item -> item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Criar nova instância de Pedido
        Pedido pedido = new Pedido();
        pedido.setUsuarioId(carrinho.getUsuarioId());
        pedido.setTokenSessao(carrinho.getTokenSessao());
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatusPedido(StatusPedido.AGUARDANDO_PAGAMENTO);
        pedido.setValorTotal(valorTotal);

        // Transferir itens do carrinho para o pedido
        for (ItemCarrinho itemCarrinho : carrinho.getItens()) {
            ItemPedido itemPedido = new ItemPedido(
                    itemCarrinho.getEdicao().getId().toString(),
                    itemCarrinho.getQuantidade(),
                    itemCarrinho.getPrecoUnitario(),
                    pedido
            );
            pedido.addItem(itemPedido);
        }

        // Salvar pedido
        pedidoRepository.persist(pedido);

        // Remover/desativar carrinho
        carrinhoRepository.delete(carrinho);

        // Retornar resposta
        return mapToResponseDTO(pedido);
    }

    @Override
    public PedidoResponseDTO obterPorId(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findByIdOptional(id);
        return pedido.map(this::mapToResponseDTO)
                .orElseThrow(() -> new ValidationException("Pedido não encontrado com ID: " + id));
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

    private PedidoResponseDTO mapToResponseDTO(Pedido pedido) {
        List<ItemPedidoDTO> itensDTO = pedido.getItens().stream()
                .map(item -> new ItemPedidoDTO(
                        item.getId(),
                        Long.parseLong(item.getProdutoId()),
                        "", // Você pode buscar o nome da edição se necessário
                        item.getQuantidade(),
                        item.getPrecoUnitario()
                ))
                .collect(Collectors.toList());

        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getUsuarioId(),
                pedido.getTokenSessao(),
                pedido.getDataPedido(),
                pedido.getStatusPedido(),
                pedido.getValorTotal(),
                itensDTO
        );
    }
}
