package br.unitins.repository;

import br.unitins.model.ItemPedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;

public class ItemPedidoRepository implements PanacheRepository<ItemPedido> {

    public List<ItemPedido> findByPedidoId(String id) {
        return find("produto_id", id).list();

    }
}
