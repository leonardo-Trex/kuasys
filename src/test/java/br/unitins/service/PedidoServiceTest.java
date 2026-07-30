package br.unitins.service;

import br.unitins.dto.itempedido.ItemPedidoCreateDTO;
import br.unitins.dto.pedido.PedidoCreateDTO;
import br.unitins.mapper.PedidoMapper;
import br.unitins.model.Edicao;
import br.unitins.model.Usuario;
import br.unitins.repository.EdicaoRepository;
import br.unitins.repository.PedidoRepository;
import br.unitins.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    @InjectMocks
    PedidoServiceImpl pedidoService;

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    EdicaoRepository edicaoRepository;

    @Mock
    PedidoRepository pedidoRepository;

    @Mock
    PedidoMapper pedidoMapper;

    @Test
    void createPedido_DeveCriarPedidoComSucesso() {
        Usuario usuario = new Usuario();
        Edicao edicao = new Edicao();
        edicao.setPreco(new BigDecimal("20.00"));

        ItemPedidoCreateDTO item = new ItemPedidoCreateDTO(1L, 2);

        PedidoCreateDTO pedidoDTO =
                new PedidoCreateDTO(
                        1L,
                        List.of(item)
                );

    }

}
