package br.unitins.service.interfaces;

import br.unitins.dto.endereco.EnderecoRequestDTO;
import br.unitins.dto.endereco.EnderecoResponseDTO;

import java.util.List;

public interface EnderecoService {

    List<EnderecoResponseDTO> listarEnderecosDoCliente(String keycloakId);

    EnderecoResponseDTO buscarPorIdECliente(Long id, String keycloakId);

    EnderecoResponseDTO salvar(String keycloakId, EnderecoRequestDTO dto);
}
