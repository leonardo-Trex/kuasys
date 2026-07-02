package br.unitins.dto.edicao;

import br.unitins.dto.colecao.ColecaoResumoDTO;
import br.unitins.dto.editora.EditoraResumoDTO;
import br.unitins.dto.quadrinho.QuadrinhoResumoDTO;
import br.unitins.model.enums.GeneroQuadrinho;
import br.unitins.model.enums.TipoCapa;

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
        Long tipoCapaId,
        TipoCapa tipoCapa,
        Long generoId,
        GeneroQuadrinho genero,
        String dimensoes,
        LocalDateTime dataCadastro,
        ColecaoResumoDTO colecao,
        EditoraResumoDTO editora,
        QuadrinhoResumoDTO quadrinho) {

}