package br.unitins.dto;

import java.time.LocalDate;

import br.unitins.model.enums.GeneroQuadrinho;
import br.unitins.model.enums.TipoCapa;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record EdicaoRequestDTO(
                @NotBlank(message = "O nome é obrigatório") String nome,

                @Positive(message = "O preço deve ser maior que zero") Double preco,

                @NotNull(message = "O número da edição é obrigatório") @Min(value = 1, message = "O número da edição deve ser no mínimo 1") Integer numero,

                @PastOrPresent(message = "A data de publicação não pode ser futura") LocalDate dataPublicacao,

                @NotBlank(message = "O ISBN é obrigatório") @Pattern(regexp = "^(?:(?=.{13}$|.{17}$)97[ -]?[0-9]{1,5}[ -]?[0-9]+[ -]?[0-9]+[ -]?[0-9]|(?=.{10}$|.{13}$)[0-9]{1,5}[ -]?[0-9]+[ -]?[0-9]+[ -]?[0-9X])$", message = "ISBN inválido") String isbn,

                @PositiveOrZero(message = "A tiragem não pode ser negativa") Integer tiragem,

                @NotNull(message = "O tipo de capa é obrigatório") TipoCapa tipoCapa,

                String dimensoes,

                @NotNull(message = "O gênero é obrigatório") GeneroQuadrinho genero) {
        public String isbnLimpo() {
                return isbn == null ? null : isbn.replaceAll("[^0-9X]", "");
        }
}
