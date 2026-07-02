package br.unitins.service.interfaces;

import br.unitins.dto.colecao.ColecaoCreateDTO;
import br.unitins.dto.colecao.ColecaoResponseDTO;

import java.util.List;

public interface ColecaoService {
    List<ColecaoResponseDTO> findAll();

    ColecaoResponseDTO findById(Long id);

    List<ColecaoResponseDTO> findByNome(String nome);

    ColecaoResponseDTO create(ColecaoCreateDTO colecao);

//    void update(Long id, ColecaoCreateDTO colecao);

    void delete(Long id);

}