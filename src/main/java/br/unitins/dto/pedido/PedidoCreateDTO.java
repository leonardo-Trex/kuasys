package br.unitins.dto.pedido;

import br.unitins.dto.itempedido.ItemPedidoCreateDTO;

import java.util.List;

public record PedidoCreateDTO(
        Long usuarioId,
        List<ItemPedidoCreateDTO> itens
) {
}
