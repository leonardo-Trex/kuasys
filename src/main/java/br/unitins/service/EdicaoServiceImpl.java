package br.unitins.service;

import br.unitins.dto.edicao.EdicaoCreateDTO;
import br.unitins.dto.edicao.EdicaoResponseDTO;
import br.unitins.mapper.EdicaoMapper;
import br.unitins.model.Colecao;
import br.unitins.model.Edicao;
import br.unitins.model.Editora;
import br.unitins.model.Quadrinho;
import br.unitins.model.enums.GeneroQuadrinho;
import br.unitins.model.enums.TipoCapa;
import br.unitins.repository.ColecaoRepository;
import br.unitins.repository.EdicaoRepository;
import br.unitins.repository.EditoraRepository;
import br.unitins.repository.QuadrinhoRepository;
import br.unitins.service.interfaces.EdicaoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

//TODO: Essa clase é muito densa, vai ficar de enfeite por enquanto.
@ApplicationScoped
public class EdicaoServiceImpl implements EdicaoService {

    @Inject
    EdicaoRepository edicaoRepository;

    @Inject
    QuadrinhoRepository quadrinhoRepository;

    @Inject
    EditoraRepository editoraRepository;

    @Inject
    ColecaoRepository colecaoRepository;

    @Inject
    EdicaoMapper mapper;


    @Override
    public List<EdicaoResponseDTO> findAll() {

        return edicaoRepository.findAll().list()
                .stream()
                .map(c -> mapper.toResponseDTO(c))
                .toList();
    }

    @Override
    public EdicaoResponseDTO findById(Long id) {

        return mapper.toResponseDTO(edicaoRepository.findById(id));
    }

    @Override
    public List<EdicaoResponseDTO> findByNome(String nome) {

//        return repository.findByNome(nome).list()
//                .stream()
//                .map(c -> mapper.toResponseDTO(c))
//                .toList();

        return null;
    }

    @Override
    @Transactional
    public EdicaoResponseDTO create(EdicaoCreateDTO dto) {
//    FIXME valores invalidos para ids!

        Edicao edicao = mapper.toEntity(dto);
        Editora editora = editoraRepository.findById(dto.editoraId());
        Quadrinho quadrinho = quadrinhoRepository.findById(dto.quadrinhoId());
        Colecao colecao = colecaoRepository.findById(dto.colecaoId());

        edicao.setColecao(colecao);
        edicao.setQuadrinho(quadrinho);
        edicao.setEditora(editora);

        edicaoRepository.persist(edicao);
        return mapper.toResponseDTO(edicao);
    }

    @Override
    @Transactional
    public void update(Long id, EdicaoCreateDTO dto) {
        Edicao e = edicaoRepository.findById(id);

        e.setNome(dto.nomeEdicao());
        e.setDescricao(dto.descricao());
        e.setPreco(dto.preco());
        e.setNumero(dto.numero());
        e.setDataPublicacao(dto.dataPublicacao());
        e.setIsbn(dto.isbn());
        e.setTiragem(dto.tiragem());


        e.setTipoCapa(TipoCapa.valueOf(dto.tipoCapaId()));
        e.setDimensoes(dto.dimensoes());

        e.setColecao(colecaoRepository.findById(dto.colecaoId()));
        e.setEditora(editoraRepository.findById(dto.editoraId()));
        e.setQuadrinho(quadrinhoRepository.findById(dto.quadrinhoId()));
    }

    @Override
    @Transactional
    public void delete(Long id) {

        edicaoRepository.deleteById(id);
    }

}
