package br.unitins.model;

import java.time.LocalDate;

import br.unitins.model.enums.TipoCapa;

public class Edicao extends Produto {
    private Integer numero;
    private LocalDate dataPublicacao;
    private String isbn;
    private Integer tiragem;
    private TipoCapa tipoCapa;
    private String dimensoes;
}
