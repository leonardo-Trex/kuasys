package br.unitins.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.unitins.model.enums.GeneroQuadrinho;
import br.unitins.model.enums.TipoCapa;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record EdicaoRequestDTO(
                @NotBlank(message = "O nome é obrigatório") String nome,

                @NotBlank(message = "A descrição é obrigatória") String descricao,

                @NotNull(message = "O preço é obrigatório") @Positive(message = "O preço deve ser maior que zero") BigDecimal preco,

                @NotNull(message = "O número da edição é obrigatório") @Min(value = 1, message = "O número da edição deve ser no mínimo 1") Integer numero,

                @PastOrPresent(message = "A data de publicação não pode ser futura") LocalDate dataPublicacao,

                @NotBlank(message = "O ISBN é obrigatório") String isbn,

                @PositiveOrZero(message = "A tiragem não pode ser negativa") Integer tiragem,

                @NotNull(message = "O tipo de capa é obrigatório") TipoCapa tipoCapa,

                @NotBlank(message = "As dimensões são obrigatórias") String dimensoes,

                @NotNull(message = "O gênero é obrigatório") GeneroQuadrinho genero,

                @NotNull(message = "A coleção é obrigatória") Long idColecao,

                @NotNull(message = "A editora é obrigatória") Long idEditora,

                @NotNull(message = "O quadrinho é obrigatório") Long idQuadrinho) {
        public String isbnLimpo() {
                return isbn == null ? null : isbn.replaceAll("[^0-9X]", "");
        }
}