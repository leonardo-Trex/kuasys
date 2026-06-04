package br.unitins.repository;

import java.util.List;

import br.unitins.model.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {

    public List<Endereco> findByUsuarioId(Long usuarioId) {
        return list("usuario.id", usuarioId);
    }
}
