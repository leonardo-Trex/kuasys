package br.unitins.Service;

import java.util.List;

import br.unitins.Service.interfaces.EditoraService;
import br.unitins.model.Editora;
import br.unitins.repository.EditoraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EditoraServiceImpl implements EditoraService {

    @Inject
    EditoraRepository repository;

    @Override
    public List<Editora> findAll() {
        return repository.findAll().list();
    }

    @Override
    public Editora findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Editora> findByNome(String nome) {
        return repository.findByNome(nome).list();
    }

    @Override
    @Transactional
    public Editora create(Editora editora) {
        repository.persist(editora);
        return editora;
    }

    @Override
    @Transactional
    public void update(Long id, Editora editora) {
        Editora e = findById(id);
        e.setNome(editora.getNome());
        e.setCnpj(editora.getCnpj());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
