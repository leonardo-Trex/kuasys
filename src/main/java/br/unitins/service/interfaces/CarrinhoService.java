package br.unitins.service.interfaces;

import br.unitins.dto.carrinho.CarrinhoRequestDTO;
import br.unitins.model.Carrinho;

public interface CarrinhoService {

    Carrinho adicionarItem(CarrinhoRequestDTO dto, String cartToken, String usuarioId);

    Carrinho obterCarrinho(String cartToken, String usuarioId);

    boolean removerItem(Carrinho carrinho, Long itemId);

    Carrinho vincularCarrinho(String tokenSessao, String usuarioId);
}
