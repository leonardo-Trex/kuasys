package br.unitins.service.interfaces;

import br.unitins.model.Quadrinista;

import java.util.List;

public interface PessoaService {
    List<Quadrinista> findAll();

    Quadrinista findById(Long id);

    List<Quadrinista> findByNome(String nome);

    Quadrinista create(Quadrinista edicao);

    void update(Long id, Quadrinista Edicao);

    void delete(Long id);
}
