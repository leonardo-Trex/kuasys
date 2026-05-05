package br.unitins.repository;

import br.unitins.model.Quadrinho;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuadrinhoRepository implements PanacheRepository<Quadrinho> {

    public PanacheQuery<Quadrinho> findByTitulo(String titulo) {
        return find("SELECT q FROM Quadrinho q WHERE UPPER(q.titulo) LIKE UPPER(?1)", "%" + titulo + "%");
    }
}