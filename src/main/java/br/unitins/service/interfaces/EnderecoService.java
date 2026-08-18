package br.unitins.service.interfaces;

import br.unitins.dto.endereco.EnderecoCreateDTO;
import br.unitins.dto.endereco.EnderecoResponseDTO;

import java.util.List;

public interface EnderecoService {

    List<EnderecoResponseDTO> findAll();

    EnderecoResponseDTO findById(Long id);

    List<EnderecoResponseDTO> findByUsuarioId(Long id);

    EnderecoResponseDTO create(EnderecoCreateDTO dto);

    void update(Long id, EnderecoCreateDTO dto);
}
