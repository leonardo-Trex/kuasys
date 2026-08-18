package br.unitins.dto.colecao;

import java.time.LocalDate;

public record ColecaoUpdateDTO(
        String nome,

        String descricao,

        Long editoraId,

        LocalDate dataInicioPublicacao,

        LocalDate dataFimPublicacao) {
}