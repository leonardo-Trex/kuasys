package br.unitins.Service.interfaces;

import java.util.List;

import br.unitins.model.Credito;

public interface CreditoService {
    List<Credito> findAll();

    Credito findById(Long id);

    List<Credito> findByFuncao(String funcao);

    Credito create(Credito credito);

    void update(Long id, Credito credito);

    void delete(Long id);
}