package br.unitins.repository;

import br.unitins.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public PanacheQuery<Usuario> findByNome(String nome) {
        return find("SELECT u FROM Usuario u WHERE UPPER(u.nome) LIKE UPPER(?1)", "%" + nome + "%");
    }

    public Usuario findByEmail(String email) {
        return find("email", email).firstResult();
    }

}
