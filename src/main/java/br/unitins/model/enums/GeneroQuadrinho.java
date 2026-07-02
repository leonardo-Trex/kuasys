package br.unitins.model.enums;

import lombok.Getter;

@Getter
public enum GeneroQuadrinho {
    ACAO(1L, "Ação"),
    AVENTURA(2L, "Aventura"),
    FICCAO_CIENTIFICA(3L, "Ficção Cientifica"),
    FANTASIA(4L, "Fantasia"),
    TERROR(5L, "Terror");

    private final Long id;
    private final String nome;

    private GeneroQuadrinho(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    //    TODO: melhorar essa verificação de null, retorno deve ser algo do tipo status inválido!
    public static GeneroQuadrinho valueOf(Long id) {
        if (id == null)
            return null;

        for (GeneroQuadrinho gq : values()) {
            if (gq.getId().equals(id))
                return gq;
        }
        return null;
    }
}
