package br.unitins.dto.itempedido;

import java.math.BigDecimal;

public record ItemPedidoCreateDTO(
        Long id,
        Long edicaoId,
        String nome,
        int quantidade,
        BigDecimal precoUnitario
) {

}
