package br.unitins.Service.interfaces;

import java.util.List;

import br.unitins.model.Quadrinho;

public interface QuadrinhoService {
    List<Quadrinho> findAll();

    Quadrinho findById(Long id);

    List<Quadrinho> findByTitulo(String titulo);

    Quadrinho create(Quadrinho quadrinho);

    void update(Long id, Quadrinho quadrinho);

    void delete(Long id);
}