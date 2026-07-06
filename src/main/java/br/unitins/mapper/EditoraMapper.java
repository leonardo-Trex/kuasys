package br.unitins.mapper;

import br.unitins.dto.editora.EditoraCreateDTO;
import br.unitins.dto.editora.EditoraResponseDTO;
import br.unitins.model.Editora;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface EditoraMapper {

    @Mapping(target = "colecoes", ignore = true)
    public Editora toEntity(EditoraCreateDTO dto);

    public EditoraResponseDTO toResponseDTO(Editora editora);

    public List<EditoraResponseDTO> toResponseDTO(List<Editora> editoras);
}
