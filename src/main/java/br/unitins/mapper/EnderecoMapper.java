package br.unitins.mapper;

import br.unitins.dto.endereco.EnderecoCreateDTO;
import br.unitins.dto.endereco.EnderecoResponseDTO;
import br.unitins.model.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface EnderecoMapper {

    @Mapping(target = "principal", ignore = true)
    public Endereco toEntity(EnderecoCreateDTO dto);

    //    O service...
    @Mapping(target = "usuarioId", source = "usuario.id")
    public EnderecoResponseDTO toResponseDTO(Endereco endereco);

    public List<EnderecoResponseDTO> toResponseDTO(List<Endereco> endereco);
}
