package br.unitins.dto;

import java.time.LocalDate;

import br.unitins.model.enums.GeneroQuadrinho;
import br.unitins.model.enums.TipoCapa;

public record EdicaoResponseDTO(
        Long id,
        String nome,
        Double preco,
        Integer numero,
        LocalDate dataPublicacao,
        String isbn,
        Integer tiragem,
        TipoCapa tipoCapa,
        String dimensoes,
        GeneroQuadrinho genero) {
}
