package br.unitins.mapper;

import br.unitins.dto.endereco.EnderecoCreateDTO;
import br.unitins.dto.endereco.EnderecoResponseDTO;
import br.unitins.model.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface EnderecoMapper {

    public Endereco toEntity(EnderecoCreateDTO dto);

    public EnderecoResponseDTO toResponseDTO(Endereco endereco);

    public List<EnderecoResponseDTO> toResponseDTO(List<Endereco> endereco);
}
