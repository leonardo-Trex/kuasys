package br.unitins.mapper;

import br.unitins.dto.ItemCarrinhoDTO;
import br.unitins.dto.CarrinhoResponseDTO;
import br.unitins.model.ItemCarrinho;
import br.unitins.model.Carrinho;
import java.util.stream.Collectors;

public class CarrinhoMapper {

    public static ItemCarrinhoDTO toItemDTO(ItemCarrinho item) {
        return new ItemCarrinhoDTO(
                item.getId(),
                item.getEdicao().getId(),
                item.getEdicao().getNome(),
                item.getQuantidade(),
                item.getPrecoUnitario());
    }

    public static CarrinhoResponseDTO toResponseDTO(Carrinho carrinho) {
        return new CarrinhoResponseDTO(
                carrinho.getId(),
                carrinho.getUsuarioId(),
                carrinho.getTokenSessao(),
                carrinho.getDataCriacao(),
                carrinho.getItens().stream()
                        .map(CarrinhoMapper::toItemDTO)
                        .collect(Collectors.toList()));
    }
}
