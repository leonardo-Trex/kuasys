package br.unitins.repository;

import br.unitins.model.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

    /**
     * Busca todos os pedidos de um usuário logado
     */
    public List<Pedido> findByUsuarioId(String usuarioId) {
        return find("usuarioId", usuarioId).list();
    }

    /**
     * Busca todos os pedidos de uma sessão anônima
     */
    public List<Pedido> findByTokenSessao(String tokenSessao) {
        return find("tokenSessao", tokenSessao).list();
    }
}
