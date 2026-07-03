package br.unitins.mapper;

import br.unitins.dto.quadrinho.QuadrinhoCreateDTO;
import br.unitins.dto.quadrinho.QuadrinhoResponseDTO;
import br.unitins.dto.quadrinho.QuadrinhoResumoDTO;
import br.unitins.model.Quadrinho;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface QuadrinhoMapper {

    public Quadrinho toEntity(QuadrinhoCreateDTO dto);

    public QuadrinhoResponseDTO toResponseDTO(Quadrinho quadrinho);

    public List<QuadrinhoResponseDTO> toResponseDTO(List<Quadrinho> quadrinho);

    public QuadrinhoResumoDTO toResumoDTO(Quadrinho quadrinho);
}