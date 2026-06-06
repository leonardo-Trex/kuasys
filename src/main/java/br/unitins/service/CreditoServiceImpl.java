package br.unitins.service;

import java.util.List;

import br.unitins.service.interfaces.CreditoService;
import br.unitins.model.Credito;
import br.unitins.repository.CreditoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CreditoServiceImpl implements CreditoService {

    @Inject
    CreditoRepository repository;

    @Override
    public List<Credito> findAll() {
        return repository.findAll().list();
    }

    @Override
    public Credito findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Credito> findByFuncao(String funcao) {
        return repository.findByFuncao(funcao).list();
    }

    @Override
    @Transactional
    public Credito create(Credito credito) {
        repository.persist(credito);
        return credito;
    }

    @Override
    @Transactional
    public void update(Long id, Credito credito) {
        Credito c = findById(id);
        c.setFuncao(credito.getFuncao());
        c.setQuadrinho(credito.getQuadrinho());
        c.setPessoa(credito.getPessoa());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}