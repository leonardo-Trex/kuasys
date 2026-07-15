package br.unitins.model;

import br.unitins.converter.StatusPedidoConverter;
import br.unitins.model.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "tb_pedido")
@Getter
@NoArgsConstructor
public class Pedido extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // TODO: verificar a necessidade disso depois
    @Column(name = "token_sessao")
    private String tokenSessao;

    @Column(name = "data_pedido", nullable = false)
    private LocalDateTime dataPedido;

    @Column(name = "status_pedido", nullable = false, length = 50)
    @Setter
    @Convert(converter = StatusPedidoConverter.class)
    private StatusPedido statusPedido = StatusPedido.PENDENTE;

    @Column(name = "valor_total", precision = 19, scale = 2, nullable = false)
    @Setter
    private BigDecimal valorTotal;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido(Usuario u) {
        this.usuario = u;
    }

    public void addItem(ItemPedido item) {
        if (item != null) {
            item.setPedido(this);
            this.itens.add(item);
        }
    }

    public void removeItem(ItemPedido item) {
        if (item != null) {
            this.itens.remove(item);
            item.setPedido(null);
        }
    }

    public List<ItemPedido> getItens() {
        return Collections.unmodifiableList(this.itens);
    }

    public void calcularTotal() {
        BigDecimal soma = BigDecimal.ZERO;

        for (ItemPedido ip : this.itens) {
            soma = soma.add(ip.calcularSubTotal());
        }

        this.valorTotal = soma;
    }


}
