package br.unitins.mapper;

import br.unitins.dto.edicao.EdicaoCreateDTO;
import br.unitins.dto.edicao.EdicaoResponseDTO;
import br.unitins.dto.edicao.EdicaoResumoDTO;
import br.unitins.model.Edicao;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI,
        uses = {
                QuadrinhoMapper.class,
                ColecaoMapper.class,
                EdicaoMapper.class,
                EnumMapper.class
        }
)
public interface EdicaoMapper {

    public Edicao toEntity(EdicaoCreateDTO dto);

    public EdicaoResponseDTO toResponseDTO(Edicao edicao);

    public List<EdicaoResponseDTO> toResponseDTO(List<Edicao> edicoes);

    public EdicaoResumoDTO toResumoDTO(Edicao edicao);

    public List<EdicaoResumoDTO> toResumoDTO(List<Edicao> edicao);
}