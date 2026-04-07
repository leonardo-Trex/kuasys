package br.unitins.Service;

import java.util.List;

import br.unitins.Service.interfaces.EdicaoService;
import br.unitins.model.Edicao;
import br.unitins.repository.EdicaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EdicaoServiceImpl implements EdicaoService {

    @Inject
    EdicaoRepository repository;

    @Override
    public List<Edicao> findAll() {
        return repository.findAll().list();
    }

    @Override
    public Edicao findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Edicao> findByNome(String nome) {
        return repository.findByNome(nome).list();
    }

    @Override
    @Transactional
    public Edicao create(Edicao edicao) {
        repository.persist(edicao);
        return edicao;
    }

    @Override
    @Transactional
    public void update(Long id, Edicao edicao) {
        Edicao e = findById(id);
        e.setNumero(null);
        e.setDimensoes(null);
        e.setDataPublicacao(null);
        e.setTiragem(null);
        e.setTipoCapa(null);
        e.setGenero(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
