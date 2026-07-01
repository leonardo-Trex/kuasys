package br.unitins.mapper;

import br.unitins.dto.produto.ProdutoResponseDTO;
import br.unitins.model.Produto;

public class ProdutoMapper {
    public static ProdutoResponseDTO toResponseDTO(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getNomeImagem()
        );
    }
}
