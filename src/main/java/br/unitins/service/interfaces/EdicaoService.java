package br.unitins.service.interfaces;

import br.unitins.dto.edicao.EdicaoCreateDTO;
import br.unitins.dto.edicao.EdicaoResponseDTO;

import java.util.List;

public interface EdicaoService {
    List<EdicaoResponseDTO> findAll();

    EdicaoResponseDTO findById(Long id);

    List<EdicaoResponseDTO> findByNome(String nome);

    EdicaoResponseDTO create(EdicaoCreateDTO dto);

//    void update(Long id, EdicaoCreateDTO dto);

    void delete(Long id);

}