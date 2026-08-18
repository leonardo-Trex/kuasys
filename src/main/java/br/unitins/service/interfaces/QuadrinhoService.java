package br.unitins.service.interfaces;

import br.unitins.dto.quadrinho.QuadrinhoCreateDTO;
import br.unitins.dto.quadrinho.QuadrinhoResponseDTO;

import java.util.List;

public interface QuadrinhoService {
    List<QuadrinhoResponseDTO> findAll();

    QuadrinhoResponseDTO findById(Long id);

    List<QuadrinhoResponseDTO> findByTitulo(String titulo);

    QuadrinhoResponseDTO create(QuadrinhoCreateDTO dto);

    void update(Long id, QuadrinhoCreateDTO dto);

    void delete(Long id);
}
