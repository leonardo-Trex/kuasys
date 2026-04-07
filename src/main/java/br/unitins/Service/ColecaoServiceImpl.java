package br.unitins.Service;

import java.util.List;

import br.unitins.Service.interfaces.ColecaoService;
import br.unitins.model.Colecao;
import br.unitins.repository.ColecaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ColecaoServiceImpl implements ColecaoService {

    @Inject
    ColecaoRepository repository;

    @Override
    public List<Colecao> findAll() {
        return repository.findAll().list();
    }

    @Override
    public Colecao findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Colecao> findByNome(String nome) {
        return repository.findByNome(nome).list();
    }

    @Override
    @Transactional
    public Colecao create(Colecao colecao) {
        repository.persist(colecao);
        return colecao;
    }

    @Override
    @Transactional
    public void update(Long id, Colecao colecao) {
        Colecao c = findById(id);
        c.setNome(colecao.getNome());
        c.setDescricao(colecao.getDescricao());
        c.setDataInicioPublicacao(colecao.getDataInicioPublicacao());
        c.setDataFimPublicacao(colecao.getDataFimPublicacao());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
