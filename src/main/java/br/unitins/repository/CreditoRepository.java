package br.unitins.repository;

import br.unitins.model.Credito;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreditoRepository implements PanacheRepository<Credito> {

    public PanacheQuery<Credito> findByFuncao(String funcao) {
        return find("SELECT c FROM Credito c WHERE UPPER(c.funcao) LIKE UPPER(?1)", "%" + funcao + "%");
    }
}