package br.unitins.service.interfaces;

import br.unitins.dto.quadrinho.QuadrinhoCreateDTO;
import br.unitins.dto.quadrinho.QuadrinhoResponseDTO;

import java.util.List;

public interface QuadrinhoService {
    List<QuadrinhoResponseDTO> findAll();

    QuadrinhoResponseDTO findById(Long id);

    List<QuadrinhoResponseDTO> findByTitulo(String titulo);

    QuadrinhoResponseDTO create(QuadrinhoCreateDTO quadrinho);

//    void update(Long id, Quadrinho quadrinho);

    void delete(Long id);
}