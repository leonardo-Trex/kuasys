package br.unitins.mapper;

import br.unitins.dto.usuario.UsuarioCreateDTO;
import br.unitins.dto.usuario.UsuarioResponseDTO;
import br.unitins.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface UsuarioMapper {

    Usuario toEntity(UsuarioCreateDTO dto);

    UsuarioResponseDTO toResponseDTO(Usuario usuario);

    List<UsuarioResponseDTO> toResponseDTO(List<Usuario> usuarios);
}
