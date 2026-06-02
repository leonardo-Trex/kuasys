package br.unitins.service.interfaces;

import br.unitins.dto.PedidoResponseDTO;

public interface PedidoService {

    PedidoResponseDTO finalizarCompra(String usuarioId);

}
