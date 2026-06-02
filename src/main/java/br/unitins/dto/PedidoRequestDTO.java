package br.unitins.dto;

public record PedidoRequestDTO(
    String cartToken,
    String usuarioId
) {

}
