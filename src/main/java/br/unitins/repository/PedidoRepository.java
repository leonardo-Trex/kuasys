package br.unitins.repository;

import br.unitins.model.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {


    public List<Pedido> findByUsuarioId(String usuarioId) {
        return find("usuario_id", usuarioId).list();
    }

//    TODO: Entender o funcionamento das sessões na minha aplicação
//    public List<Pedido> findByTokenSessao(String tokenSessao) {
//        return find("tokenSessao", tokenSessao).list();
//    }
}
