package br.unitins.mapper;

import br.unitins.dto.itempedido.ItemPedidoCreateDTO;
import br.unitins.dto.pedido.PedidoResponseDTO;
import br.unitins.model.ItemPedido;
import br.unitins.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI, uses = EnumMapper.class)
public interface PedidoMapper {

    //    TODO a lógica de pedido precisa estar muito consistente para mexer nesses caras.
//    @Mapping(target = "edicaoId", source = "produto.id")
//    @Mapping(target = "nome", source = "edicao.nome")
    @Mapping(target = "edicaoId", source = "produto.id")
    public ItemPedidoCreateDTO toItemDTO(ItemPedido item);

    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "statusPedidoId", source = "statusPedido")
//    @Mapping(target = "statusPedido", source = "statusPedido")
    public PedidoResponseDTO toResponseDTO(Pedido pedido);

    public List<PedidoResponseDTO> toResponseDTOList(List<Pedido> pedidos);
}
