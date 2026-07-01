package br.unitins.dto.edicao;

import br.unitins.dto.colecao.ColecaoResponseDTO;
import br.unitins.dto.editora.EditoraResponseDTO;
import br.unitins.dto.quadrinho.QuadrinhoResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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