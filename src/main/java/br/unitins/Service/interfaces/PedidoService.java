package br.unitins.Service.interfaces;

import br.unitins.dto.PedidoRequestDTO;
import br.unitins.dto.PedidoResponseDTO;

public interface PedidoService {

    PedidoResponseDTO finalizarCompra(PedidoRequestDTO dto);

    PedidoResponseDTO obterPorId(Long id);

}
