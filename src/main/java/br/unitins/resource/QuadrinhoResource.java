package br.unitins.resource;

import br.unitins.dto.quadrinho.QuadrinhoRequestDTO;
import br.unitins.dto.quadrinho.QuadrinhoResponseDTO;
import br.unitins.mapper.QuadrinhoMapper;
import br.unitins.model.Quadrinho;
import br.unitins.service.interfaces.QuadrinhoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.List;

@Path("/quadrinhos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@Authenticated
public class QuadrinhoResource {

    @Inject
    QuadrinhoService service;

    @GET
    public Response buscarTodo() {
        List<QuadrinhoResponseDTO> lista = service.findAll()
                .stream()
                .map(QuadrinhoMapper::toResponseDTO)
                .toList();

        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPeloId(@PathParam("id") Long id) {
        return Response.ok(QuadrinhoMapper.toResponseDTO(service.findById(id))).build();
    }

    @DELETE
    @Path("/{id}")
//    @RolesAllowed("admin")
    public Response deletar(@PathParam("id") Long id) {
        service.delete(id);

        return Response.noContent().build();
    }

    @POST
//    @RolesAllowed("admin")
    public Response incluir(@Valid QuadrinhoRequestDTO dto) {
        Quadrinho quadrinho = service.create(QuadrinhoMapper.toEntity(dto));

        return Response
                .status(Status.CREATED)
                .entity(QuadrinhoMapper.toResponseDTO(quadrinho))
                .build();
    }

    @PUT
    @Path("/{id}")
//    @RolesAllowed("admin")
    public Response alterar(@PathParam("id") Long id, QuadrinhoRequestDTO dto) {
        service.update(id, QuadrinhoMapper.toEntity(dto));

        return Response.ok().build();
    }
}