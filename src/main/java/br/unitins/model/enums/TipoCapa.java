package br.unitins.model.enums;

import lombok.Getter;

@Getter
public enum TipoCapa {
    BROCHURA(1L, "Brochura"),
    CAPA_DURA(2L, "Capa Dura"),
    ESPECIAL(3L, "Especial"),
    LUXO(4L, "Luxo");

    private final Long id;
    private final String nome;

    private TipoCapa(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static TipoCapa valueOf(Long id) {
        if (id == null)
            return null;

        for (TipoCapa tc : values()) {
            if (tc.getId().equals(id))
                return tc;
        }
        return null;
    }
}
