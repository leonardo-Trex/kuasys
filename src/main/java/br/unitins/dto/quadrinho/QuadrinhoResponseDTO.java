package br.unitins.dto.quadrinho;

public record QuadrinhoResponseDTO(
        Long id,
        String titulo,
        String sinopse,
        String genero) {
}