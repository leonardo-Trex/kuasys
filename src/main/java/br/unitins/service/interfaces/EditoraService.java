package br.unitins.service.interfaces;

import java.util.List;

import br.unitins.model.Editora;

public interface EditoraService {
    List<Editora> findAll();

    Editora findById(Long id);

    List<Editora> findByNome(String nome);

    Editora create(Editora editora);

    void update(Long id, Editora editora);

    void delete(Long id);
}
