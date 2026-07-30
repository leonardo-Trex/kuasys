package br.unitins.dto.edicao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EdicaoCreateDTO(
        String nomeEdicao,

        String descricao,

        BigDecimal preco,

        Integer numero,

        LocalDate dataPublicacao,

        String isbn,

        Integer tiragem,

        Long tipoCapaId,

        String dimensoes,

        Long colecaoId,

        Long editoraId,

        Long quadrinhoId,

        Integer estoque,

        String nomeImagem
) {
}