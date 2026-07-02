package br.unitins.service.interfaces;

import br.unitins.dto.pedido.PedidoResponseDTO;

// TODO: preciso entender esse cara
public interface PedidoService {

    PedidoResponseDTO finalizarCompra(String usuarioId);

}
