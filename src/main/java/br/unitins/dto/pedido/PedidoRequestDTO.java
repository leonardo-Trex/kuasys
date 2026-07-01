package br.unitins.dto.pedido;

public record PedidoRequestDTO(
        String cartToken,
        String usuarioId
) {

}
