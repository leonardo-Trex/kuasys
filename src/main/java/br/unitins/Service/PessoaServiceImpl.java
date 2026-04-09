package br.unitins.Service;

import java.util.List;

import br.unitins.Service.interfaces.PessoaService;
import br.unitins.model.Pessoa;
import br.unitins.repository.PessoaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PessoaServiceImpl implements PessoaService {

    @Inject
    PessoaRepository repository;

    @Override
    public List<Pessoa> findAll() {
        return repository.findAll().list();
    }

    @Override
    public Pessoa findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Pessoa> findByNome(String nome) {
        return repository.findByNome(nome).list();
    }

    @Override
    @Transactional
    public Pessoa create(Pessoa pessoa) {
        repository.persist(pessoa);
        return pessoa;
    }

    @Override
    @Transactional
    public void update(Long id, Pessoa pessoa) {
        Pessoa p = findById(id);
        p.setNome(pessoa.getNome());
        p.setNacionalidade(pessoa.getNacionalidade());
        p.setDataNascimento(pessoa.getDataNascimento());

    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
