package br.unitins.model.enums;

public enum StatusPedido {
    AGUARDANDO_PAGAMENTO(1l, "Aguardando Pagamento"),
    PAGO(2l, "Pago"),
    ENVIADO(3l, "Enviado"),
    ENTREGUE(4l, "Entregue"),
    CANCELADO(5l, "Cancelado");

    private final Long ID;
    private final String NOME;

    private StatusPedido(Long id, String nome) {
        this.ID = id;
        this.NOME = nome;
    }

    public Long getID() {
        return ID;
    }

    public String getNOME() {
        return NOME;
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
