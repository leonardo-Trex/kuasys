package br.unitins.service;

import br.unitins.dto.colecao.ColecaoCreateDTO;
import br.unitins.dto.colecao.ColecaoResponseDTO;
import br.unitins.mapper.ColecaoMapper;
import br.unitins.model.Colecao;
import br.unitins.repository.ColecaoRepository;
import br.unitins.service.interfaces.ColecaoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ColecaoServiceImpl implements ColecaoService {

    @Inject
    ColecaoRepository repository;

    @Inject
    ColecaoMapper mapper;

    @Override
    public List<ColecaoResponseDTO> findAll() {

        return repository.findAll().list()
                .stream()
                .map(c -> mapper.toResponseDTO(c))
                .toList();
    }

    @Override
    public ColecaoResponseDTO findById(Long id) {

        Colecao c = repository.findById(id);
        return mapper.toResponseDTO(c);
    }

    @Override
    public List<ColecaoResponseDTO> findByNome(String nome) {

        return repository.findByNome(nome).list()
                .stream()
                .map(c -> mapper.toResponseDTO(c))
                .toList();
    }

    @Override
    @Transactional
    public ColecaoResponseDTO create(ColecaoCreateDTO dto) {

        Colecao c = mapper.toEntity(dto);
        repository.persist(c);
        return mapper.toResponseDTO(c);
    }

//    @Override
//    @Transactional
//    public void update(Long id, Colecao colecao) {
//        Colecao c = findById(id);
//        c.setNome(colecao.getNome());
//        c.setDescricao(colecao.getDescricao());
//        c.setDataInicioPublicacao(colecao.getDataInicioPublicacao());
//        c.setDataFimPublicacao(colecao.getDataFimPublicacao());
//    }

    @Override
    @Transactional
    public void delete(Long id) {

        repository.deleteById(id);
    }

}
