package br.unitins.repository;

import br.unitins.model.Carrinho;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CarrinhoRepository implements PanacheRepository<Carrinho> {

    /**
     * Busca um carrinho pela sessão (usuário anônimo)
     */
    public Carrinho findByTokenSessao(String tokenSessao) {
        return find("tokenSessao", tokenSessao).firstResult();
    }

    /**
     * Busca um carrinho pelo usuário logado
     */
    public Carrinho findByUsuarioId(String usuarioId) {
        return find("usuarioId", usuarioId).firstResult();
    }
}
