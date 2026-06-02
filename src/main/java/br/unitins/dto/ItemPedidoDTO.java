package br.unitins.dto;

import java.math.BigDecimal;

public record ItemPedidoDTO(
    Long id,
    Long edicaoId,
    String nome,
    int quantidade,
    BigDecimal precoUnitario
) {

}
