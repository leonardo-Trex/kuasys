package br.unitins.mapper;

import br.unitins.dto.ItemPedidoDTO;
import br.unitins.dto.PedidoResponseDTO;
import br.unitins.model.ItemPedido;
import br.unitins.model.Pedido;
import java.util.stream.Collectors;

public class PedidoMapper {

    public static ItemPedidoDTO toItemDTO(ItemPedido item) {
        return new ItemPedidoDTO(
                item.getId(),
                Long.parseLong(item.getProdutoId()),
                "",
                item.getQuantidade(),
                item.getPrecoUnitario()
        );
    }

    public static PedidoResponseDTO toResponseDTO(Pedido pedido) {
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getUsuarioId(),
                pedido.getTokenSessao(),
                pedido.getDataPedido(),
                pedido.getStatusPedido(),
                pedido.getValorTotal(),
                pedido.getItens().stream()
                        .map(PedidoMapper::toItemDTO)
                        .collect(Collectors.toList())
        );
    }
}
