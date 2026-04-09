package br.unitins.Service.interfaces;

import java.util.List;

import br.unitins.model.Pessoa;

public interface PessoaService {
    List<Pessoa> findAll();

    Pessoa findById(Long id);

    List<Pessoa> findByNome(String nome);

    Pessoa create(Pessoa edicao);

    void update(Long id, Pessoa Edicao);

    void delete(Long id);
}
