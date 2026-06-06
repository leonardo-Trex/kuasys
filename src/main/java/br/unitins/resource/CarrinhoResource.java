//package br.unitins.resource;
//
//import br.unitins.service.interfaces.CarrinhoService;
//import br.unitins.dto.CarrinhoRequestDTO;
//import br.unitins.dto.CarrinhoVincularDTO;
//import br.unitins.mapper.CarrinhoMapper;
//import br.unitins.model.Carrinho;
//import io.quarkus.security.Authenticated;
//import jakarta.annotation.Nullable;
//import jakarta.inject.Inject;
//import jakarta.validation.Valid;
//import jakarta.ws.rs.*;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//import jakarta.ws.rs.core.Response.Status;
//import org.eclipse.microprofile.jwt.JsonWebToken;
//
//@Path("/carrinho")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//public class CarrinhoResource {
//
//    @Inject
//    CarrinhoService carrinhoService;
//
////    @Inject
////    @Nullable
////    JsonWebToken jwt;
//
//    /**
//     * POST /carrinho/itens
//     * Adiciona um item ao carrinho do usuário (logado ou anônimo)
//     * Se o usuário está logado (JWT presente), usa usuarioId
//     * Se anônimo, usa o Header 'X-Cart-Token'
//     * Se o carrinho não existir, cria um novo
//     * Se o produto já existe no carrinho, incrementa a quantidade
//     */
//    @POST
//    @Path("/itens")
//    public Response adicionarItem(
//            @Valid CarrinhoRequestDTO dto,
//            @HeaderParam("X-Cart-Token") String cartToken) {
//
//        String usuarioId = null;
//        if (jwt != null && jwt.getRawToken() != null) {
//            usuarioId = jwt.getSubject();
//        }
//
//        Carrinho carrinho = carrinhoService.adicionarItem(dto, cartToken, usuarioId);
//
//        return Response.ok(CarrinhoMapper.toResponseDTO(carrinho))
//                .header("X-Cart-Token", carrinho.getTokenSessao())
//                .build();
//    }
//
//    /**
//     * GET /carrinho
//     * Retorna o carrinho do usuário (logado ou anônimo)
//     * Se logado (JWT), busca por usuarioId
//     * Se anônimo, busca pelo Header 'X-Cart-Token'
//     */
//    @GET
//    public Response obterCarrinho(@HeaderParam("X-Cart-Token") String cartToken) {
//        String usuarioId = null;
//        if (jwt != null && jwt.getRawToken() != null) {
//            usuarioId = jwt.getSubject();
//        }
//
//        Carrinho carrinho = carrinhoService.obterCarrinho(cartToken, usuarioId);
//        if (carrinho == null) {
//            if (usuarioId == null || usuarioId.isBlank()) {
//                return Response.status(Status.BAD_REQUEST)
//                        .entity("X-Cart-Token é obrigatório para usuários anônimos")
//                        .build();
//            }
//            return Response.status(Status.NOT_FOUND)
//                    .entity("Carrinho não encontrado")
//                    .build();
//        }
//
//        return Response.ok(CarrinhoMapper.toResponseDTO(carrinho))
//                .header("X-Cart-Token", carrinho.getTokenSessao())
//                .build();
//    }
//
//    /**
//     * DELETE /carrinho/itens/{id}
//     * Remove um item do carrinho
//     */
//    @DELETE
//    @Path("/itens/{id}")
//    public Response removerItem(
//            @PathParam("id") Long itemId,
//            @HeaderParam("X-Cart-Token") String cartToken) {
//
//        String usuarioId = null;
//        if (jwt != null && jwt.getRawToken() != null) {
//            usuarioId = jwt.getSubject();
//        }
//
//        Carrinho carrinho = carrinhoService.obterCarrinho(cartToken, usuarioId);
//        if (carrinho == null) {
//            return Response.status(Status.NOT_FOUND)
//                    .entity("Carrinho não encontrado")
//                    .build();
//        }
//
//        boolean removed = carrinhoService.removerItem(carrinho, itemId);
//        if (!removed) {
//            return Response.status(Status.NOT_FOUND)
//                    .entity("Item não encontrado no carrinho")
//                    .build();
//        }
//
//        return Response.ok(CarrinhoMapper.toResponseDTO(carrinho)).build();
//    }
//
//    /**
//     * PATCH /carrinho/vincular
//     * Endpoint protegido com @Authenticated
//     * Vincula um carrinho anônimo ao usuário logado
//     * Se o usuário já tiver um carrinho logado, faz o merge dos itens
//     * e deleta o carrinho anônimo
//     */
//    @PATCH
//    @Path("/vincular")
//    @Authenticated
//    public Response vincularCarrinho(@Valid CarrinhoVincularDTO dto) {
//
//        // Obtém o usuarioId do JWT
//        String usuarioId = jwt.getSubject();
//
//        // Busca o carrinho anônimo
//        try {
//            Carrinho carrinhoFinal = carrinhoService.vincularCarrinho(dto.tokenSessao(),
//                    usuarioId);
//            if (carrinhoFinal == null) {
//                return Response.status(Status.NOT_FOUND)
//                        .entity("Carrinho anônimo não encontrado")
//                        .build();
//            }
//            return Response.ok(CarrinhoMapper.toResponseDTO(carrinhoFinal)).build();
//        } catch (IllegalStateException e) {
//            return Response.status(Status.BAD_REQUEST)
//                    .entity(e.getMessage())
//                    .build();
//        }
//    }
//}
