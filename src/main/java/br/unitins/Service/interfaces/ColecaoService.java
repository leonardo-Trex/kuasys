package br.unitins.Service.interfaces;

import java.util.List;

import br.unitins.model.Colecao;

public interface ColecaoService {
    List<Colecao> findAll();

    Colecao findById(Long id);

    List<Colecao> findByNome(String nome);

    Colecao create(Colecao colecao);

    void update(Long id, Colecao Edicao);

    void delete(Long id);

}