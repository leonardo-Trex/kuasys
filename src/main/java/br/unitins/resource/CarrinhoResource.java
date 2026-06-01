package br.unitins.resource;

import br.unitins.dto.CarrinhoRequestDTO;
import br.unitins.dto.CarrinhoVincularDTO;
import br.unitins.mapper.CarrinhoMapper;
import br.unitins.model.Carrinho;
import br.unitins.model.ItemCarrinho;
import br.unitins.repository.CarrinhoRepository;
import io.quarkus.security.Authenticated;
import jakarta.annotation.Nullable;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.Optional;
import java.util.UUID;

@Path("/carrinho")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarrinhoResource {

    @Inject
    CarrinhoRepository carrinhoRepository;

    @Inject
    @Nullable
    JsonWebToken jwt;

    /**
     * POST /carrinho/itens
     * Adiciona um item ao carrinho do usuário (logado ou anônimo)
     * Se o usuário está logado (JWT presente), usa usuarioId
     * Se anônimo, usa o Header 'X-Cart-Token'
     * Se o carrinho não existir, cria um novo
     * Se o produto já existe no carrinho, incrementa a quantidade
     */
    @POST
    @Path("/itens")
    @Transactional
    public Response adicionarItem(
            @Valid CarrinhoRequestDTO dto,
            @HeaderParam("X-Cart-Token") String cartToken) {

        // Determina se é usuário logado ou anônimo
        String usuarioId = null;
        String tokenSessao = null;
        Carrinho carrinho;

        if (jwt != null && jwt.getRawToken() != null) {
            // Usuário logado
            usuarioId = jwt.getSubject();
            carrinho = carrinhoRepository.findByUsuarioId(usuarioId);
            if (carrinho == null) {
                carrinho = new Carrinho(usuarioId, null);
                carrinhoRepository.persist(carrinho);
            }
        } else if (cartToken != null && !cartToken.isBlank()) {
            // Usuário anônimo
            tokenSessao = cartToken;
            carrinho = carrinhoRepository.findByTokenSessao(tokenSessao);
            if (carrinho == null) {
                carrinho = new Carrinho(null, tokenSessao);
                carrinhoRepository.persist(carrinho);
            }
        } else {
            // Nenhuma forma de identificação fornecida
            // Gera um novo token para o usuário anônimo
            tokenSessao = UUID.randomUUID().toString();
            carrinho = new Carrinho(null, tokenSessao);
            carrinhoRepository.persist(carrinho);
        }

        // Verifica se o produto já existe no carrinho
        Optional<ItemCarrinho> itemExistente = carrinho.getItens().stream()
                .filter(item -> item.getProdutoId().equals(dto.produtoId()))
                .findFirst();

        if (itemExistente.isPresent()) {
            // Incrementa a quantidade
            itemExistente.get().setQuantidade(itemExistente.get().getQuantidade() + dto.quantidade());
        } else {
            // Cria um novo item
            ItemCarrinho novoItem = new ItemCarrinho(dto.produtoId(), dto.quantidade(), carrinho);
            carrinho.getItens().add(novoItem);
        }

        carrinhoRepository.persist(carrinho);

        return Response.ok(CarrinhoMapper.toResponseDTO(carrinho))
                .header("X-Cart-Token", carrinho.getTokenSessao())
                .build();
    }

    /**
     * GET /carrinho
     * Retorna o carrinho do usuário (logado ou anônimo)
     * Se logado (JWT), busca por usuarioId
     * Se anônimo, busca pelo Header 'X-Cart-Token'
     */
    @GET
    public Response obterCarrinho(@HeaderParam("X-Cart-Token") String cartToken) {
        Carrinho carrinho;

        if (jwt != null && jwt.getRawToken() != null) {
            // Usuário logado
            String usuarioId = jwt.getSubject();
            carrinho = carrinhoRepository.findByUsuarioId(usuarioId);
        } else if (cartToken != null && !cartToken.isBlank()) {
            // Usuário anônimo
            carrinho = carrinhoRepository.findByTokenSessao(cartToken);
        } else {
            // Sem identificação
            return Response.status(Status.BAD_REQUEST)
                    .entity("X-Cart-Token é obrigatório para usuários anônimos")
                    .build();
        }

        if (carrinho == null) {
            return Response.status(Status.NOT_FOUND)
                    .entity("Carrinho não encontrado")
                    .build();
        }

        return Response.ok(CarrinhoMapper.toResponseDTO(carrinho))
                .header("X-Cart-Token", carrinho.getTokenSessao())
                .build();
    }

    /**
     * DELETE /carrinho/itens/{id}
     * Remove um item do carrinho
     */
    @DELETE
    @Path("/itens/{id}")
    @Transactional
    public Response removerItem(
            @PathParam("id") Long itemId,
            @HeaderParam("X-Cart-Token") String cartToken) {

        Carrinho carrinho;

        if (jwt != null && jwt.getRawToken() != null) {
            // Usuário logado
            String usuarioId = jwt.getSubject();
            carrinho = carrinhoRepository.findByUsuarioId(usuarioId);
        } else if (cartToken != null && !cartToken.isBlank()) {
            // Usuário anônimo
            carrinho = carrinhoRepository.findByTokenSessao(cartToken);
        } else {
            return Response.status(Status.BAD_REQUEST)
                    .entity("X-Cart-Token é obrigatório para usuários anônimos")
                    .build();
        }

        if (carrinho == null) {
            return Response.status(Status.NOT_FOUND)
                    .entity("Carrinho não encontrado")
                    .build();
        }

        // Encontra e remove o item
        boolean removed = carrinho.getItens().removeIf(item -> item.getId().equals(itemId));

        if (!removed) {
            return Response.status(Status.NOT_FOUND)
                    .entity("Item não encontrado no carrinho")
                    .build();
        }

        carrinhoRepository.persist(carrinho);

        return Response.ok(CarrinhoMapper.toResponseDTO(carrinho)).build();
    }

    /**
     * PATCH /carrinho/vincular
     * Endpoint protegido com @Authenticated
     * Vincula um carrinho anônimo ao usuário logado
     * Se o usuário já tiver um carrinho logado, faz o merge dos itens
     * e deleta o carrinho anônimo
     */
    @PATCH
    @Path("/vincular")
    @Authenticated
    @Transactional
    public Response vincularCarrinho(@Valid CarrinhoVincularDTO dto) {

        // Obtém o usuarioId do JWT
        String usuarioId = jwt.getSubject();

        // Busca o carrinho anônimo
        Carrinho carrinhoAnonimo = carrinhoRepository.findByTokenSessao(dto.tokenSessao());
        if (carrinhoAnonimo == null) {
            return Response.status(Status.NOT_FOUND)
                    .entity("Carrinho anônimo não encontrado")
                    .build();
        }

        // Verifica se o carrinho anônimo já está vinculado
        if (carrinhoAnonimo.getUsuarioId() != null) {
            return Response.status(Status.BAD_REQUEST)
                    .entity("Este carrinho já está vinculado a um usuário")
                    .build();
        }

        // Busca o carrinho do usuário logado
        Carrinho carrinhoUsuario = carrinhoRepository.findByUsuarioId(usuarioId);

        if (carrinhoUsuario == null) {
            // O usuário não tem carrinho ainda, então vincula o anônimo
            carrinhoAnonimo.setUsuarioId(usuarioId);
            carrinhoAnonimo.setTokenSessao(null);
            carrinhoRepository.persist(carrinhoAnonimo);
        } else {
            // O usuário já tem um carrinho, faz o merge
            // Adiciona todos os itens do carrinho anônimo ao carrinho do usuário
            for (ItemCarrinho itemAnonimo : carrinhoAnonimo.getItens()) {
                Optional<ItemCarrinho> itemExistente = carrinhoUsuario.getItens().stream()
                        .filter(item -> item.getProdutoId().equals(itemAnonimo.getProdutoId()))
                        .findFirst();

                if (itemExistente.isPresent()) {
                    // Incrementa a quantidade do item existente
                    itemExistente.get().setQuantidade(itemExistente.get().getQuantidade() + itemAnonimo.getQuantidade());
                } else {
                    // Adiciona o item do carrinho anônimo
                    ItemCarrinho novoItem = new ItemCarrinho(
                            itemAnonimo.getProdutoId(),
                            itemAnonimo.getQuantidade(),
                            carrinhoUsuario
                    );
                    carrinhoUsuario.getItens().add(novoItem);
                }
            }

            carrinhoRepository.persist(carrinhoUsuario);

            // Deleta o carrinho anônimo
            carrinhoRepository.delete(carrinhoAnonimo);
        }

        // Retorna o carrinho vinculado
        Carrinho carrinhoFinal = carrinhoRepository.findByUsuarioId(usuarioId);
        return Response.ok(CarrinhoMapper.toResponseDTO(carrinhoFinal)).build();
    }
}
