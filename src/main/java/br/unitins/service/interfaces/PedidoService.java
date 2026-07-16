package br.unitins.service.interfaces;

import br.unitins.dto.pedido.PedidoCreateDTO;
import br.unitins.dto.pedido.PedidoResponseDTO;

import java.util.List;

// TODO: preciso entender esse cara
public interface PedidoService {

    PedidoResponseDTO finalizarCompra(String usuarioId);

    PedidoResponseDTO createPedido(PedidoCreateDTO dto);

    List<PedidoResponseDTO> findByUsuario();

//    BigDecimal total();
}
