package br.unitins.dto.colecao;

import br.unitins.dto.edicao.EdicaoResumoDTO;

import java.time.LocalDate;
import java.util.List;

public record ColecaoResponseDTO(
        Long id,
        String nome,
        String descricao,
        LocalDate dataInicioPublicacao,
        LocalDate dataFimPublicacao,
        List<EdicaoResumoDTO> edicoes) {
}