package br.unitins.service;

import br.unitins.dto.endereco.EnderecoCreateDTO;
import br.unitins.dto.endereco.EnderecoResponseDTO;
import br.unitins.mapper.EnderecoMapper;
import br.unitins.model.Endereco;
import br.unitins.repository.EnderecoRepository;
import br.unitins.service.interfaces.EnderecoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {

    @Inject
    EnderecoRepository repository;

    @Inject
    EnderecoMapper mapper;

    @Override
    public List<EnderecoResponseDTO> findAll() {

        List<Endereco> lista = repository.findAll().list();
        return mapper.toResponseDTO(lista);
    }

    @Override
    public EnderecoResponseDTO findById(Long id) {

        Endereco e = repository.findById(id);
        return mapper.toResponseDTO(e);
    }

    @Override
    public List<EnderecoResponseDTO> findByUsuarioId(Long id) {

        List<Endereco> lista = repository.findByUsuarioId(id);
        return mapper.toResponseDTO(lista);
    }

    @Override
    public EnderecoResponseDTO create(EnderecoCreateDTO dto) {

        Endereco e = mapper.toEntity(dto);
        repository.persist(e);
        return mapper.toResponseDTO(e);
    }


}
