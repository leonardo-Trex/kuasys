package br.unitins.dto.quadrinho;

import br.unitins.model.enums.GeneroQuadrinho;

public record QuadrinhoCreateDTO(
        String titulo,

        String sinopse,

        GeneroQuadrinho genero) {
}