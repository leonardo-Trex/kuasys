package br.unitins.model.enums;

import lombok.Getter;

@Getter
public enum TipoCapa {
    BROCHURA(1L, "Brochura"),
    CAPA_DURA(2L, "Capa Dura"),
    ESPECIAL(3L, "Especial"),
    LUXO(4L, "Luxo");

    private final Long ID;
    private final String NOME;

    private TipoCapa(Long id, String nome) {
        this.ID = id;
        this.NOME = nome;
    }

    public static TipoCapa valueOf(Long id) {
        if (id == null)
            return null;

        for (TipoCapa tc : values()) {
            if (tc.getID().equals(id))
                return tc;
        }
        return null;
    }
}
