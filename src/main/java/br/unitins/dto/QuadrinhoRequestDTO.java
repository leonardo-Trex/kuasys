package br.unitins.dto;

import br.unitins.model.enums.GeneroQuadrinho;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record QuadrinhoRequestDTO(
                @NotBlank(message = "O título é obrigatório") String titulo,

                String sinopse,

                @NotNull(message = "O gênero é obrigatório") GeneroQuadrinho genero) {
}