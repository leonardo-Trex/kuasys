package br.unitins.mapper;

import br.unitins.dto.EditoraRequestDTO;
import br.unitins.dto.EditoraResponseDTO;
import br.unitins.model.Editora;

public class EditoraMapper {

    public static Editora toEntity(EditoraRequestDTO dto) {
        if (dto == null)
            return null;

        Editora editora = new Editora();
        editora.setNome(dto.nome());
        editora.setCnpj(dto.cnpj());

        return editora;
    }

    public static EditoraResponseDTO toResponseDTO(Editora editora) {
        if (editora == null)
            return null;

        return new EditoraResponseDTO(
                editora.getId(),
                editora.getNome(),
                editora.getCnpj());
    }
}
