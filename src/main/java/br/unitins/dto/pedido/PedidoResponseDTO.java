package br.unitins.dto.pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PedidoResponseDTO(
        Long id,
        String usuarioId,
//      TODO: vai ser necessário somente na integração do keycloak
//        String tokenSessao,
        LocalDateTime dataPedido,
        Long statusPedidoID,
        String statusPedido,
        BigDecimal valorTotal
//      TODO isso vai ficar pra daqui a pouco
//        List<ItemPedidoDTO> itens
) {

}
