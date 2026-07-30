package br.unitins.mapper;

import br.unitins.dto.edicao.EdicaoCreateDTO;
import br.unitins.dto.edicao.EdicaoResponseDTO;
import br.unitins.dto.edicao.EdicaoResumoDTO;
import br.unitins.model.Edicao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI,
        uses = {
                EnumMapper.class
        }
)
public interface EdicaoMapper {

    //  O service lida com as associações!
    @Mapping(target = "colecao", ignore = true)
    @Mapping(target = "editora", ignore = true)
    @Mapping(target = "quadrinho", ignore = true)
    @Mapping(target = "tipoCapa", source = "tipoCapaId")
    @Mapping(target = "nome", source = "nomeEdicao")
    public Edicao toEntity(EdicaoCreateDTO dto);

    @Mapping(target = "tipoCapaId", source = "tipoCapa")
    public EdicaoResponseDTO toResponseDTO(Edicao edicao);

    public List<EdicaoResponseDTO> toResponseDTO(List<Edicao> edicoes);

    public EdicaoResumoDTO toResumoDTO(Edicao edicao);

    public List<EdicaoResumoDTO> toResumoDTO(List<Edicao> edicao);
}