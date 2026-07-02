package br.unitins.service.interfaces;

import br.unitins.dto.editora.EditoraCreateDTO;
import br.unitins.dto.editora.EditoraResponseDTO;

import java.util.List;

public interface EditoraService {
    List<EditoraResponseDTO> findAll();

    EditoraResponseDTO findById(Long id);

    List<EditoraResponseDTO> findByNome(String nome);

    EditoraResponseDTO create(EditoraCreateDTO editora);

//    void update(Long id, Editora editora);

    void delete(Long id);
}
