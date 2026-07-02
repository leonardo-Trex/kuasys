package br.unitins.mapper;

import br.unitins.dto.colecao.ColecaoCreateDTO;
import br.unitins.dto.colecao.ColecaoResponseDTO;
import br.unitins.dto.colecao.ColecaoResumoDTO;
import br.unitins.model.Colecao;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI, uses = EdicaoMapper.class)
public interface ColecaoMapper {

    public Colecao toEntity(ColecaoCreateDTO dto);

    public ColecaoResponseDTO toResponseDTO(Colecao colecao);

    public List<ColecaoResponseDTO> toResponseDTO(List<Colecao> colecoes);

    public ColecaoResumoDTO toResumoDTO(Colecao colecao);

    public List<ColecaoResumoDTO> toResumoDTO(List<Colecao> colecoes);
}