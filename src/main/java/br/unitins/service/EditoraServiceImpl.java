package br.unitins.service;

import br.unitins.dto.editora.EditoraCreateDTO;
import br.unitins.dto.editora.EditoraResponseDTO;
import br.unitins.mapper.EditoraMapper;
import br.unitins.model.Editora;
import br.unitins.repository.EditoraRepository;
import br.unitins.service.interfaces.EditoraService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class EditoraServiceImpl implements EditoraService {

    @Inject
    EditoraRepository repository;

    @Inject
    EditoraMapper mapper;

    @Override
    public List<EditoraResponseDTO> findAll() {


        return repository.findAll().list()
                .stream()
                .map(c -> mapper.toResponseDTO(c))
                .toList();
    }

    @Override
    public EditoraResponseDTO findById(Long id) {

        Editora e = repository.findById(id);
        return mapper.toResponseDTO(e);
    }

    @Override
    public List<EditoraResponseDTO> findByNome(String nome) {

        return repository.findByNome(nome).list()
                .stream()
                .map(c -> mapper.toResponseDTO(c))
                .toList();
    }

    @Override
    @Transactional
    public EditoraResponseDTO create(EditoraCreateDTO dto) {

        Editora e = mapper.toEntity(dto);
        repository.persist(e);
        return mapper.toResponseDTO(e);
    }

    @Override
    @Transactional
    public void update(Long id, EditoraCreateDTO dto) {
        Editora e = repository.findById(id);

        e.setNome(dto.nome());
        e.setCnpj(dto.cnpj());
    }

    @Override
    @Transactional
    public void delete(Long id) {

        repository.deleteById(id);
    }

}
