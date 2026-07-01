package br.unitins.service.interfaces;

import br.unitins.dto.pedido.PedidoResponseDTO;

public interface PedidoService {

    PedidoResponseDTO finalizarCompra(String usuarioId);

}
