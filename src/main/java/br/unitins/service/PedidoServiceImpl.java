/*
 * Essa classe é tenso
 *
 *
 *
 *
 *
 *
 * */

package br.unitins.service;

import br.unitins.dto.itempedido.ItemPedidoCreateDTO;
import br.unitins.dto.pedido.PedidoCreateDTO;
import br.unitins.dto.pedido.PedidoResponseDTO;
import br.unitins.mapper.PedidoMapper;
import br.unitins.model.Edicao;
import br.unitins.model.ItemPedido;
import br.unitins.model.Pedido;
import br.unitins.model.Usuario;
import br.unitins.repository.EdicaoRepository;
import br.unitins.repository.PedidoRepository;
import br.unitins.repository.UsuarioRepository;
import br.unitins.service.interfaces.PedidoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    EdicaoRepository edicaoRepository;

    @Inject
    PedidoMapper mapper;

    @Inject
    PedidoRepository pedidoRepository;

    @Override
    public PedidoResponseDTO finalizarCompra(String usuarioId) {
        return null;
    }

    @Override
    @Transactional
    public PedidoResponseDTO createPedido(PedidoCreateDTO dto) {

//        TODO Metodo auxiliar para lidar com exceptions
        Usuario usuario = usuarioRepository.findById(dto.usuarioId());

        Pedido pedido = new Pedido(usuario);

        for (ItemPedidoCreateDTO item : dto.itens()) {
            pedido.addItem(createItemPedido(item)); // addItem define internamente o Pedido de ItemPedido
        }

        pedido.calcularTotal();

        pedidoRepository.persist(pedido);

        return mapper.toResponseDTO(pedido);
    }

    //    TODO vai ficar na geladeira por um tempo
    @Override
    public List<PedidoResponseDTO> findByUsuario() {
        return List.of();
    }

    //    TODO aplicar validações no parametro e na busca ao banco.
    private ItemPedido createItemPedido(ItemPedidoCreateDTO dto) {
        ItemPedido ip = new ItemPedido();

//        TODO Método auxiliar para lidar com exceptions
        Edicao e = edicaoRepository.findById(dto.edicaoId());
        ip.setProduto(e);
        ip.setQuantidade(dto.quantidade());
        ip.setPrecoUnitario(e.getPreco());
        return ip;
    }

}
