package br.unitins.mapper;

import br.unitins.dto.quadrinho.QuadrinhoRequestDTO;
import br.unitins.dto.quadrinho.QuadrinhoResponseDTO;
import br.unitins.model.Quadrinho;

public class QuadrinhoMapper {

    public static Quadrinho toEntity(QuadrinhoRequestDTO dto) {
        if (dto == null)
            return null;

        Quadrinho quadrinho = new Quadrinho();

        quadrinho.setTitulo(dto.titulo());
        quadrinho.setSinopse(dto.sinopse());
        quadrinho.setGenero(dto.genero());

        return quadrinho;
    }

    public static QuadrinhoResponseDTO toResponseDTO(Quadrinho quadrinho) {
        if (quadrinho == null)
            return null;

        return new QuadrinhoResponseDTO(
                quadrinho.getId(),
                quadrinho.getTitulo(),
                quadrinho.getSinopse(),
                quadrinho.getGenero().name());
    }
}