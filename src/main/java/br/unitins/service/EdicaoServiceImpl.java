package br.unitins.service;

import br.unitins.dto.edicao.EdicaoRequestDTO;
import br.unitins.mapper.EdicaoMapper;
import br.unitins.model.Edicao;
import br.unitins.repository.ColecaoRepository;
import br.unitins.repository.EdicaoRepository;
import br.unitins.repository.EditoraRepository;
import br.unitins.repository.QuadrinhoRepository;
import br.unitins.service.interfaces.EdicaoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class EdicaoServiceImpl implements EdicaoService {

    @Inject
    EdicaoRepository repository;

    @Inject
    ColecaoRepository colecaoRepository;

    @Inject
    EditoraRepository editoraRepository;

    @Inject
    QuadrinhoRepository quadrinhoRepository;

    @Override
    public List<Edicao> findAll() {
        return repository.findAll().list();
    }

    @Override
    public Edicao findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Edicao> findByNome(String nome) {
        return repository.findByNome(nome).list();
    }

    @Override
    @Transactional
    public Edicao create(EdicaoRequestDTO dto) {
        Edicao edicao = EdicaoMapper.toEntity(dto);
        edicao.setColecao(colecaoRepository.findByIdOptional(dto.idColecao()).orElseThrow(() -> new NotFoundException("Coleção não encontrada")));
        edicao.setEditora(editoraRepository.findByIdOptional(dto.idEditora()).orElseThrow(() -> new NotFoundException("Editora não encontrada")));
        edicao.setQuadrinho(quadrinhoRepository.findByIdOptional(dto.idQuadrinho()).orElseThrow(() -> new NotFoundException("Quadrinho não encontrado")));
        repository.persist(edicao);
        return edicao;
    }

    @Override
    @Transactional
    public void update(Long id, EdicaoRequestDTO dto) {
        Edicao e = findById(id);
        if (e == null) {
            throw new NotFoundException("Edição não encontrada");
        }
        e.setNome(dto.nome());
        e.setDescricao(dto.descricao());
        e.setPreco(dto.preco());
        e.setNumero(dto.numero());
        e.setDataPublicacao(dto.dataPublicacao());
        e.setIsbn(dto.isbnLimpo());
        e.setTiragem(dto.tiragem());
        e.setTipoCapa(dto.tipoCapa());
        e.setDimensoes(dto.dimensoes());
        e.setGenero(dto.genero());
        e.setColecao(colecaoRepository.findByIdOptional(dto.idColecao()).orElseThrow(() -> new NotFoundException("Coleção não encontrada")));
        e.setEditora(editoraRepository.findByIdOptional(dto.idEditora()).orElseThrow(() -> new NotFoundException("Editora não encontrada")));
        e.setQuadrinho(quadrinhoRepository.findByIdOptional(dto.idQuadrinho()).orElseThrow(() -> new NotFoundException("Quadrinho não encontrado")));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
