package br.unitins.repository;

import br.unitins.model.ItemPedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ItemPedidoRepository implements PanacheRepository<ItemPedido> {

    public List<ItemPedido> findByPedidoId(long id) {
        return find("pedido.id", id).list();

    }
}
