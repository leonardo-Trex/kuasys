package br.unitins.repository;

import br.unitins.model.Quadrinista;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaRepository implements PanacheRepository<Quadrinista> {

    public PanacheQuery<Quadrinista> findByNome(String nome) {
        return find("SELECT e FROM tb_edicao e WHERE UPPER(e.nome) LIKE UPPER(?1)", "%" + nome + "%");
    }
}
