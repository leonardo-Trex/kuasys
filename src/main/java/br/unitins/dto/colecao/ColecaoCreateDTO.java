package br.unitins.dto.colecao;

import java.time.LocalDate;

public record ColecaoCreateDTO(
        String nome,

        String descricao,

        LocalDate dataInicioPublicacao,

        LocalDate dataFimPublicacao) {
}