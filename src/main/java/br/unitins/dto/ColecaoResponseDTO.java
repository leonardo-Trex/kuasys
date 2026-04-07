package br.unitins.dto;

import java.time.LocalDate;
import java.util.List;

import br.unitins.model.Edicao;

public record ColecaoResponseDTO(
                Long id,
                String nome,
                String descricao,
                LocalDate dataInicioPublicacao,
                LocalDate dataFimPublicacao,
                List<Edicao> edicoes) {
}