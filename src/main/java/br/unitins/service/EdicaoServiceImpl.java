package br.unitins.service;

import br.unitins.dto.edicao.EdicaoCreateDTO;
import br.unitins.dto.edicao.EdicaoResponseDTO;
import br.unitins.mapper.EdicaoMapper;
import br.unitins.repository.EdicaoRepository;
import br.unitins.service.interfaces.EdicaoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

//TODO: Essa clase é muito densa, vai ficar de enfeite por enquanto.
@ApplicationScoped
public class EdicaoServiceImpl implements EdicaoService {

    @Inject
    EdicaoRepository repository;

    @Inject
    EdicaoMapper mapper;


    @Override
    public List<EdicaoResponseDTO> findAll() {

//        return repository.findAll().list()
//                .stream()
//                .map(c -> mapper.toResponseDTO(c))
//                .toList();

        return null;
    }

    @Override
    public EdicaoResponseDTO findById(Long id) {

//        return repository.findById(id);
        return null;
    }

    @Override
    public List<EdicaoResponseDTO> findByNome(String nome) {

//        return repository.findByNome(nome).list()
//                .stream()
//                .map(c -> mapper.toResponseDTO(c))
//                .toList();

        return null;
    }

    //    TODO: Esse cara é muito denso, a lógica de implementação vem depois
    @Override
    @Transactional
    public EdicaoResponseDTO create(EdicaoCreateDTO dto) {

        return null;
    }

//    @Override
//    @Transactional
//    public void update(Long id, EdicaoCreateDTO dto) {
//        Edicao e = findById(id);
//        if (e == null) {
//            throw new NotFoundException("Edição não encontrada");
//        }
//        e.setNome(dto.nome());
//        e.setDescricao(dto.descricao());
//        e.setPreco(dto.preco());
//        e.setNumero(dto.numero());
//        e.setDataPublicacao(dto.dataPublicacao());
//        e.setIsbn(dto.isbnLimpo());
//        e.setTiragem(dto.tiragem());
//        e.setTipoCapa(dto.tipoCapa());
//        e.setDimensoes(dto.dimensoes());
//        e.setGenero(dto.genero());
//        e.setColecao(colecaoRepository.findByIdOptional(dto.idColecao()).orElseThrow(() -> new NotFoundException("Coleção não encontrada")));
//        e.setEditora(editoraRepository.findByIdOptional(dto.idEditora()).orElseThrow(() -> new NotFoundException("Editora não encontrada")));
//        e.setQuadrinho(quadrinhoRepository.findByIdOptional(dto.idQuadrinho()).orElseThrow(() -> new NotFoundException("Quadrinho não encontrado")));
//    }

    @Override
    @Transactional
    public void delete(Long id) {

        repository.deleteById(id);
    }

}
