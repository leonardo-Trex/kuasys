package br.unitins.dto;

import br.unitins.model.enums.StatusPedido;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO(
    Long id,
    String usuarioId,
    String tokenSessao,
    LocalDateTime dataPedido,
    StatusPedido statusPedido,
    BigDecimal valorTotal,
    List<ItemPedidoDTO> itens
) {

}
