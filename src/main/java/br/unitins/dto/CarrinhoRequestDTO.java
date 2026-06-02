package br.unitins.dto;

import java.math.BigDecimal;

public record CarrinhoRequestDTO(
    String produtoId,
    int quantidade,
    BigDecimal precoUnitario
) {

}
