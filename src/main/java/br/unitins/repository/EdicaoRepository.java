package br.unitins.repository;

import br.unitins.model.Edicao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EdicaoRepository implements PanacheRepository<Edicao> {

}
