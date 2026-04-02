package br.unitins.repository;

import br.unitins.model.Edicao;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EdicaoRepository implements PanacheRepository<Edicao> {

    public PanacheQuery<Edicao> findByNome(String nome) {
        return find("SELECT e FROM tb_edicao e WHERE UPPER(e.nome) LIKE UPPER(?1)", "%" + nome + "%");
    }
}
