package br.unitins.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum GeneroQuadrinho {
    ACAO(1l, "Ação"),
    AVENTURA(2l, "Aventura"),
    FICCAO_CIENTIFICA(3l, "Ficção Cientifica"),
    FANTASIA(4l, "Fantasia"),
    TERROR(5l, "Terror");

    private final Long ID;
    private final String NOME;

    private GeneroQuadrinho(Long id, String nome) {
        this.ID = id;
        this.NOME = nome;
    }

    public Long getID() {
        return ID;
    }

    public String getNOME() {
        return NOME;
    }

    public static GeneroQuadrinho valueOf(Long id) {
        if (id == null)
            return null;

        for (GeneroQuadrinho gq : values()) {
            if (gq.getID().equals(id))
                return gq;
        }
        return null;
    }
}
