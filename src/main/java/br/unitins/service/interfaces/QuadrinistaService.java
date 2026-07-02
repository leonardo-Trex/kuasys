package br.unitins.service.interfaces;

import br.unitins.dto.quadrinista.QuadrinistaCreateDTO;
import br.unitins.dto.quadrinista.QuadrinistaResponseDTO;

import java.util.List;

public interface QuadrinistaService {
    List<QuadrinistaResponseDTO> findAll();

    QuadrinistaResponseDTO findById(Long id);

    List<QuadrinistaResponseDTO> findByNome(String nome);

    QuadrinistaResponseDTO create(QuadrinistaCreateDTO dto);

//    void update(Long id, QuadrinistaCreateDTO dto);

    void delete(Long id);
}
