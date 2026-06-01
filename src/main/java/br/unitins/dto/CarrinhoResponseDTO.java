package br.unitins.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CarrinhoResponseDTO(
    Long id,
    String usuarioId,
    String tokenSessao,
    LocalDateTime dataCriacao,
    List<ItemCarrinhoDTO> itens
) {

}
