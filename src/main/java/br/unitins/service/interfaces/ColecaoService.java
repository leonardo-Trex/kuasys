package br.unitins.service.interfaces;

import br.unitins.dto.colecao.ColecaoCreateDTO;
import br.unitins.dto.colecao.ColecaoResponseDTO;

import java.util.List;

public interface ColecaoService {
    List<ColecaoResponseDTO> findAll();

    ColecaoResponseDTO findById(Long id);

    List<ColecaoResponseDTO> findByNome(String nome);

    ColecaoResponseDTO create(ColecaoCreateDTO dto);

    void update(Long id, ColecaoCreateDTO dto);

    void delete(Long id);

}
