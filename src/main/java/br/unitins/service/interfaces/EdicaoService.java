package br.unitins.service.interfaces;

import java.util.List;

import br.unitins.model.Edicao;

public interface EdicaoService {
    List<Edicao> findAll();

    Edicao findById(Long id);

    List<Edicao> findByNome(String nome);

    Edicao create(br.unitins.dto.EdicaoRequestDTO dto);

    void update(Long id, br.unitins.dto.EdicaoRequestDTO dto);

    void delete(Long id);

}