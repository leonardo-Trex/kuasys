package br.unitins.Service;

import br.unitins.Service.interfaces.EnderecoService;
import br.unitins.dto.EnderecoResponseDTO;
import br.unitins.mapper.EnderecoMapper;
import br.unitins.model.Endereco;
import br.unitins.repository.EnderecoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {

    @Inject
    EnderecoRepository repository;

    @Override
    public List<EnderecoResponseDTO> listarEnderecosDoCliente(Long usuarioId) {
        List<Endereco> enderecos = repository.findByUsuarioId(usuarioId);
        return enderecos.stream()
                .map(EnderecoMapper::toResponseDTO)
                .toList();
    }

    @Override
    public EnderecoResponseDTO buscarPorIdECliente(Long id, Long usuarioId) {
        Endereco endereco = repository.findById(id);
        
        if (endereco == null) {
            throw new NotFoundException("Endereço não encontrado");
        }
        
        if (!endereco.getUsuario().getId().equals(usuarioId)) {
            throw new NotFoundException("Endereço não pertence ao usuário");
        }
        
        return EnderecoMapper.toResponseDTO(endereco);
    }
}
