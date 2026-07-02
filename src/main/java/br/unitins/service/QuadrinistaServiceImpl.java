package br.unitins.service;

import br.unitins.model.Quadrinista;
import br.unitins.repository.QuadrinistaRepository;
import br.unitins.service.interfaces.PessoaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PessoaServiceImpl implements PessoaService {

    @Inject
    QuadrinistaRepository repository;

    @Override
    public List<Quadrinista> findAll() {
        return repository.findAll().list();
    }

    @Override
    public Quadrinista findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Quadrinista> findByNome(String nome) {
        return repository.findByNome(nome).list();
    }

    @Override
    @Transactional
    public Quadrinista create(Quadrinista quadrinista) {
        repository.persist(quadrinista);
        return quadrinista;
    }

    @Override
    @Transactional
    public void update(Long id, Quadrinista quadrinista) {
        Quadrinista p = findById(id);
        p.setNome(quadrinista.getNome());
        p.setNacionalidade(quadrinista.getNacionalidade());
        p.setDataNascimento(quadrinista.getDataNascimento());

    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
