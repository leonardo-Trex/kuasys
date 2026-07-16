package br.unitins.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_item_pedido")
@Getter
@NoArgsConstructor
public class ItemPedido extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    @Setter
    private Produto produto;

    @Column(nullable = false)
    @Setter
//    TODO talvez um Long para evitar pequenas conversões?
    private Integer quantidade;

    @Column(name = "preco_unitario", precision = 19, scale = 2, nullable = false)
    @Setter
    private BigDecimal precoUnitario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pedido_id", nullable = false)
    @Setter
    private Pedido pedido;

    BigDecimal calcularSubTotal() {
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

}
