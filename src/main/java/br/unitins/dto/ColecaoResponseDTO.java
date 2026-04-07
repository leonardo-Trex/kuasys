package br.unitins.dto;

import java.time.LocalDate;

public record ColecaoResponseDTO(
        Long id,
        String nome,
        String descricao,
        LocalDate dataInicioPublicacao,
        LocalDate dataFimPublicacao) {
}