package br.unitins.model.enums;

import lombok.Getter;

@Getter
public enum GeneroQuadrinho {
    ACAO(1L, "Ação"),
    AVENTURA(2L, "Aventura"),
    FICCAO_CIENTIFICA(3L, "Ficção Cientifica"),
    FANTASIA(4L, "Fantasia"),
    TERROR(5L, "Terror");

    private final Long ID;
    private final String NOME;

    private GeneroQuadrinho(Long id, String nome) {
        this.ID = id;
        this.NOME = nome;
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
