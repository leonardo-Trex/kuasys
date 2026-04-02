package br.unitins.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import br.unitins.model.Edicao;

public record EdicaoResponseDTO(
                Long id,
                String nome,
                String descricao,
                BigDecimal preco,
                Integer numero,
                LocalDate dataPublicacao,
                String isbn,
                Integer tiragem,
                String tipoCapa, // Podemos retornar o label ou a String do Enum
                String genero,
                String dimensoes,
                LocalDateTime dataCadastro) {

}