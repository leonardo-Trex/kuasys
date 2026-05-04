package br.unitins.repository;

import br.unitins.model.Editora;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EditoraRepository implements PanacheRepository<Editora> {

    public PanacheQuery<Editora> findByNome(String nome) {
        return find("SELECT e FROM tb_editora e WHERE UPPER(e.nome) LIKE UPPER(?1)", "%" + nome + "%");
    }
}
