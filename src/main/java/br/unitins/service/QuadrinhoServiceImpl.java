package br.unitins.service;

import br.unitins.dto.quadrinho.QuadrinhoCreateDTO;
import br.unitins.dto.quadrinho.QuadrinhoResponseDTO;
import br.unitins.mapper.QuadrinhoMapper;
import br.unitins.model.Quadrinho;
import br.unitins.repository.QuadrinhoRepository;
import br.unitins.service.interfaces.QuadrinhoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class QuadrinhoServiceImpl implements QuadrinhoService {

    @Inject
    QuadrinhoRepository repository;

    @Inject
    QuadrinhoMapper mapper;

    @Override
    public List<QuadrinhoResponseDTO> findAll() {

        return repository.findAll().list()
                .stream()
                .map(c -> mapper.toResponseDTO(c))
                .toList();
    }

    @Override
    public QuadrinhoResponseDTO findById(Long id) {

        Quadrinho q = repository.findById(id);
        return mapper.toResponseDTO(q);
    }

    @Override
    public List<QuadrinhoResponseDTO> findByTitulo(String titulo) {

        return repository.findByTitulo(titulo).list()
                .stream()
                .map(c -> mapper.toResponseDTO(c))
                .toList();
    }

    @Override
    @Transactional
    public QuadrinhoResponseDTO create(QuadrinhoCreateDTO dto) {

        Quadrinho q = mapper.toEntity(dto);
        repository.persist(q);
        return mapper.toResponseDTO(q);
    }

//    @Override
//    @Transactional
//    public void update(Long id, Quadrinho quadrinho) {
//        Quadrinho q = findById(id);
//        q.setTitulo(quadrinho.getTitulo());
//        q.setSinopse(quadrinho.getSinopse());
//        q.setGenero(quadrinho.getGenero());
//    }

    @Override
    @Transactional
    public void delete(Long id) {

        repository.deleteById(id);
    }
}