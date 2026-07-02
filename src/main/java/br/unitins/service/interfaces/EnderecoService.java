package br.unitins.service.interfaces;

import br.unitins.dto.endereco.EnderecoCreateDTO;
import br.unitins.dto.endereco.EnderecoResponseDTO;

import java.util.List;

public interface EnderecoService {

    List<EnderecoResponseDTO> findAll();

    EnderecoResponseDTO findById(Long id);

    List<EnderecoResponseDTO> findByUsuarioId(Long id);

    //    TODO: talvez esse cara precise de um Id de Usuário também
    EnderecoResponseDTO create(EnderecoCreateDTO dto);
}
