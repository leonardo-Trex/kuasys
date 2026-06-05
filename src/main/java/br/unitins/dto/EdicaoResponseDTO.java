package br.unitins.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.unitins.dto.ColecaoResponseDTO;
import br.unitins.dto.EditoraResponseDTO;
import br.unitins.dto.QuadrinhoResponseDTO;

public record EdicaoResponseDTO(
                Long id,
                String nome,
                String descricao,
                BigDecimal preco,
                Integer numero,
                LocalDate dataPublicacao,
                String isbn,
                Integer tiragem,
                String tipoCapa,
                String genero,
                String dimensoes,
                LocalDateTime dataCadastro,
                ColecaoResponseDTO colecao,
                EditoraResponseDTO editora,
                QuadrinhoResponseDTO quadrinho) {

}