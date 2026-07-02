package br.unitins.dto.edicao;

import br.unitins.model.enums.GeneroQuadrinho;
import br.unitins.model.enums.TipoCapa;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EdicaoCreateDTO(
        String nome,

        String descricao,

        BigDecimal preco,

        Integer numero,

        LocalDate dataPublicacao,

        String isbn,

        Integer tiragem,

        TipoCapa tipoCapa,

        String dimensoes,

        GeneroQuadrinho genero,

        Long idColecao,

        Long idEditora,

        Long idQuadrinho) {


}