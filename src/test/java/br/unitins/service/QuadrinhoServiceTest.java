package br.unitins.service;

import br.unitins.dto.quadrinho.QuadrinhoCreateDTO;
import br.unitins.dto.quadrinho.QuadrinhoResponseDTO;
import br.unitins.mapper.QuadrinhoMapper;
import br.unitins.model.Quadrinho;
import br.unitins.model.enums.GeneroQuadrinho;
import br.unitins.repository.QuadrinhoRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuadrinhoServiceTest {

    @InjectMocks
    QuadrinhoServiceImpl service;

    @Mock
    QuadrinhoRepository repository;

    @Mock
    QuadrinhoMapper mapper;

    @Mock
    PanacheQuery<Quadrinho> query;

    @Test
    void findAll_deveRetornarTodosOsQuadrinhosMapeados() {
        Quadrinho primeiroQuadrinho = novoQuadrinho("Watchmen", GeneroQuadrinho.ACAO);
        Quadrinho segundoQuadrinho = novoQuadrinho("Sandman", GeneroQuadrinho.FANTASIA);
        QuadrinhoResponseDTO primeiraResposta = novaResposta("Watchmen", "Ação");
        QuadrinhoResponseDTO segundaResposta = novaResposta("Sandman", "Fantasia");

        when(repository.findAll()).thenReturn(query);
        when(query.list()).thenReturn(List.of(primeiroQuadrinho, segundoQuadrinho));
        when(mapper.toResponseDTO(primeiroQuadrinho)).thenReturn(primeiraResposta);
        when(mapper.toResponseDTO(segundoQuadrinho)).thenReturn(segundaResposta);

        List<QuadrinhoResponseDTO> resultado = service.findAll();

        assertEquals(List.of(primeiraResposta, segundaResposta), resultado);
        verify(repository).findAll();
        verify(query).list();
        verify(mapper).toResponseDTO(primeiroQuadrinho);
        verify(mapper).toResponseDTO(segundoQuadrinho);
    }

    @Test
    void findAll_deveRetornarListaVaziaQuandoNaoExistiremQuadrinhos() {
        when(repository.findAll()).thenReturn(query);
        when(query.list()).thenReturn(List.of());

        List<QuadrinhoResponseDTO> resultado = service.findAll();

        assertEquals(List.of(), resultado);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void findById_deveRetornarQuadrinhoMapeado() {
        Quadrinho quadrinho = novoQuadrinho("Maus", GeneroQuadrinho.AVENTURA);
        QuadrinhoResponseDTO resposta = novaResposta("Maus", "Aventura");
        when(repository.findById(7L)).thenReturn(quadrinho);
        when(mapper.toResponseDTO(quadrinho)).thenReturn(resposta);

        QuadrinhoResponseDTO resultado = service.findById(7L);

        assertSame(resposta, resultado);
        verify(repository).findById(7L);
        verify(mapper).toResponseDTO(quadrinho);
    }

    @Test
    void findByTitulo_deveRetornarOsQuadrinhosEncontradosMapeados() {
        Quadrinho quadrinho = novoQuadrinho("Batman: Ano Um", GeneroQuadrinho.ACAO);
        QuadrinhoResponseDTO resposta = novaResposta("Batman: Ano Um", "Ação");
        when(repository.findByTitulo("batman")).thenReturn(query);
        when(query.list()).thenReturn(List.of(quadrinho));
        when(mapper.toResponseDTO(quadrinho)).thenReturn(resposta);

        List<QuadrinhoResponseDTO> resultado = service.findByTitulo("batman");

        assertEquals(List.of(resposta), resultado);
        verify(repository).findByTitulo("batman");
        verify(query).list();
        verify(mapper).toResponseDTO(quadrinho);
    }

    @Test
    void create_deveMapearPersistirERetornarOQuadrinhoCriado() {
        QuadrinhoCreateDTO dto = new QuadrinhoCreateDTO(
                "Akira",
                "Uma história cyberpunk.",
                GeneroQuadrinho.FICCAO_CIENTIFICA
        );
        Quadrinho quadrinho = novoQuadrinho("Akira", GeneroQuadrinho.FICCAO_CIENTIFICA);
        QuadrinhoResponseDTO resposta = novaResposta("Akira", "Ficção Cientifica");
        when(mapper.toEntity(dto)).thenReturn(quadrinho);
        when(mapper.toResponseDTO(quadrinho)).thenReturn(resposta);

        QuadrinhoResponseDTO resultado = service.create(dto);

        assertSame(resposta, resultado);
        InOrder ordem = inOrder(mapper, repository);
        ordem.verify(mapper).toEntity(dto);
        ordem.verify(repository).persist(quadrinho);
        ordem.verify(mapper).toResponseDTO(quadrinho);
    }

    @Test
    void delete_deveExcluirQuadrinhoPeloId() {
        service.delete(12L);

        verify(repository).deleteById(12L);
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    private Quadrinho novoQuadrinho(String titulo, GeneroQuadrinho genero) {
        Quadrinho quadrinho = new Quadrinho();
        quadrinho.setTitulo(titulo);
        quadrinho.setSinopse("Sinopse de " + titulo);
        quadrinho.setGenero(genero);
        return quadrinho;
    }

    private QuadrinhoResponseDTO novaResposta(String titulo, String genero) {
        return new QuadrinhoResponseDTO(null, titulo, "Sinopse de " + titulo, genero);
    }
}
