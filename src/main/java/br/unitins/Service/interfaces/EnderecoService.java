package br.unitins.Service.interfaces;

import br.unitins.dto.EnderecoResponseDTO;
import java.util.List;

public interface EnderecoService {

    List<EnderecoResponseDTO> listarEnderecosDoCliente(Long usuarioId);

    EnderecoResponseDTO buscarPorIdECliente(Long id, Long usuarioId);
}
