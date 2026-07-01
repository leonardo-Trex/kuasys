package br.unitins.service.interfaces;

import br.unitins.dto.edicao.EdicaoRequestDTO;
import br.unitins.model.Edicao;

import java.util.List;

public interface EdicaoService {
    List<Edicao> findAll();

    Edicao findById(Long id);

    List<Edicao> findByNome(String nome);

    Edicao create(EdicaoRequestDTO dto);

    void update(Long id, EdicaoRequestDTO dto);

    void delete(Long id);

}