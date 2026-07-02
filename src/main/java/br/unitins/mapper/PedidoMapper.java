package br.unitins.mapper;

import br.unitins.dto.itempedido.ItemPedidoDTO;
import br.unitins.dto.pedido.PedidoResponseDTO;
import br.unitins.model.ItemPedido;
import br.unitins.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI, uses = EnumMapper.class)
public interface PedidoMapper {

    public ItemPedidoDTO toItemDTO(ItemPedido item);

    public PedidoResponseDTO toResponseDTO(Pedido pedido);

    public List<PedidoResponseDTO> toResponseDTOList(List<Pedido> pedidos);
}
