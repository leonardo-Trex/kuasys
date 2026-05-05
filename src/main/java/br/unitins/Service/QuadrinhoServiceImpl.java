package br.unitins.Service;

import java.util.List;

import br.unitins.Service.interfaces.QuadrinhoService;
import br.unitins.model.Quadrinho;
import br.unitins.repository.QuadrinhoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class QuadrinhoServiceImpl implements QuadrinhoService {

    @Inject
    QuadrinhoRepository repository;

    @Override
    public List<Quadrinho> findAll() {
        return repository.findAll().list();
    }

    @Override
    public Quadrinho findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Quadrinho> findByTitulo(String titulo) {
        return repository.findByTitulo(titulo).list();
    }

    @Override
    @Transactional
    public Quadrinho create(Quadrinho quadrinho) {
        repository.persist(quadrinho);
        return quadrinho;
    }

    @Override
    @Transactional
    public void update(Long id, Quadrinho quadrinho) {
        Quadrinho q = findById(id);
        q.setTitulo(quadrinho.getTitulo());
        q.setSinopse(quadrinho.getSinopse());
        q.setGenero(quadrinho.getGenero());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}