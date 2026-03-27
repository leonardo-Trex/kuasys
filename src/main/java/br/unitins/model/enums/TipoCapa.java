package br.unitins.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum TipoCapa {
    BROCHURA(1l, "Brochura"),
    CAPA_DURA(2l, "Capa Dura"),
    ESPECIAL(3l, "Especial"),
    LUXO(4l, "Luxo");

    private final Long ID;
    private final String NOME;

    private TipoCapa(Long id, String nome) {
        this.ID = id;
        this.NOME = nome;
    }

    public Long getID() {
        return ID;
    }

    public String getNOME() {
        return NOME;
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
