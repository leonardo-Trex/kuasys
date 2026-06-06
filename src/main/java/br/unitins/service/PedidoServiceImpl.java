package br.unitins.service;

import br.unitins.service.interfaces.CarrinhoService;
import br.unitins.service.interfaces.PedidoService;
import br.unitins.dto.PedidoResponseDTO;
import br.unitins.mapper.PedidoMapper;
import br.unitins.model.Carrinho;
import br.unitins.model.ItemPedido;
import br.unitins.model.Pedido;
import br.unitins.model.enums.StatusPedido;
import br.unitins.repository.CarrinhoRepository;
import br.unitins.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;

import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    CarrinhoService carrinhoService;

    @Inject
    CarrinhoRepository carrinhoRepository;

    @Inject
    PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public PedidoResponseDTO finalizarCompra(String usuarioId) {
        Carrinho carrinho = carrinhoService.obterCarrinho(null, usuarioId);

        if (carrinho == null || carrinho.getItens() == null || carrinho.getItens().isEmpty()) {
            throw new BadRequestException("Carrinho não encontrado ou está vazio");
        }

        Pedido pedido = new Pedido();
        pedido.setUsuarioId(usuarioId);
        pedido.setTokenSessao(carrinho.getTokenSessao());
        pedido.setDataPedido(java.time.LocalDateTime.now());
        pedido.setStatusPedido(StatusPedido.PENDENTE);

        BigDecimal valorTotal = carrinho.getItens().stream()
                .map(item -> item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        pedido.setValorTotal(valorTotal);

        carrinho.getItens().stream()
                .map(itemCarrinho -> new ItemPedido(
                        itemCarrinho.getEdicao().getId().toString(),
                        itemCarrinho.getQuantidade(),
                        itemCarrinho.getPrecoUnitario(),
                        pedido
                ))
                .forEach(pedido::addItem);

        pedidoRepository.persist(pedido);
        carrinhoRepository.delete(carrinho);

        return PedidoMapper.toResponseDTO(pedido);
    }
    
    public List<PedidoResponseDTO> listarPedidosDoCliente(String usuarioId) {
        List<Pedido> pedidos = pedidoRepository.findByUsuarioId(usuarioId);
        return PedidoMapper.toResponseDTOList(pedidos);
    }
}
