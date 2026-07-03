package br.unitins.mapper;

import br.unitins.dto.colecao.ColecaoCreateDTO;
import br.unitins.dto.colecao.ColecaoResponseDTO;
import br.unitins.dto.colecao.ColecaoResumoDTO;
import br.unitins.model.Colecao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI,
        uses = {
                EdicaoMapper.class,
                EditoraMapper.class
        })
public interface ColecaoMapper {

    //    O service usa o id do dto para buscar os objetos associados!
    @Mapping(target = "editora", ignore = true)
    @Mapping(target = "edicoes", ignore = true)
    public Colecao toEntity(ColecaoCreateDTO dto);

    public ColecaoResponseDTO toResponseDTO(Colecao colecao);

    public List<ColecaoResponseDTO> toResponseDTO(List<Colecao> colecoes);

    public ColecaoResumoDTO toResumoDTO(Colecao colecao);

    public List<ColecaoResumoDTO> toResumoDTO(List<Colecao> colecoes);
}