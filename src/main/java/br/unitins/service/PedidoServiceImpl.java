package br.unitins.service;

import br.unitins.dto.pedido.PedidoResponseDTO;
import br.unitins.service.interfaces.PedidoService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Override
    public PedidoResponseDTO finalizarCompra(String usuarioId) {
        return null;
    }
}
