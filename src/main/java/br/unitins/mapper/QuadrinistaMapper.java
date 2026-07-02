package br.unitins.mapper;

import br.unitins.dto.quadrinista.QuadrinistaCreateDTO;
import br.unitins.dto.quadrinista.QuadrinistaResponseDTO;
import br.unitins.model.Quadrinista;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

// O que é CDI? é o singleton desse cara mas qual o contexto por trás?
@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface QuadrinistaMapper {

    public Quadrinista toEntity(QuadrinistaCreateDTO dto);

    public QuadrinistaResponseDTO toResponseDTO(Quadrinista quadrinista);

    public List<QuadrinistaResponseDTO> toResponseDTO(List<Quadrinista> lista);
}