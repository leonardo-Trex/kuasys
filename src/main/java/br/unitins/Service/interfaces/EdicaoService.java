package br.unitins.Service.interfaces;

import java.util.List;

import br.unitins.model.Edicao;

public interface EdicaoService {
    List<Edicao> findAll();

    Edicao findById(Long id);

    List<Edicao> findByNome(String nome);

    Edicao create(Edicao edicao);

    void update(Long id, Edicao Edicao);

    void delete(Long id);

}