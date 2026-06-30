package br.unitins.model.enums;

import lombok.Getter;

@Getter
public enum StatusPedido {
    PENDENTE(1L, "Pendente"),
    AGUARDANDO_PAGAMENTO(2L, "Aguardando Pagamento"),
    PAGO(3L, "Pago"),
    ENVIADO(4L, "Enviado"),
    ENTREGUE(5L, "Entregue"),
    CANCELADO(6L, "Cancelado");

    private final Long ID;
    private final String NOME;

    private StatusPedido(Long id, String nome) {
        this.ID = id;
        this.NOME = nome;
    }

    public static StatusPedido valueOf(Long id) {
        if (id == null)
            return null;

        for (StatusPedido tc : values()) {
            if (tc.getID().equals(id))
                return tc;
        }
        return null;
    }
}
