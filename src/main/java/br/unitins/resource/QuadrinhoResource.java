package br.unitins.resource;

import java.util.List;

import br.unitins.Service.interfaces.QuadrinhoService;
import br.unitins.dto.QuadrinhoRequestDTO;
import br.unitins.dto.QuadrinhoResponseDTO;
import br.unitins.mapper.QuadrinhoMapper;
import br.unitins.model.Quadrinho;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/quadrinhos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
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
    public Response buscarPeloId(Long id) {
        return Response.ok(QuadrinhoMapper.toResponseDTO(service.findById(id))).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(Long id) {
        service.delete(id);

        return Response.noContent().build();
    }

    @POST
    public Response incluir(@Valid QuadrinhoRequestDTO dto) {
        Quadrinho quadrinho = service.create(QuadrinhoMapper.toEntity(dto));

        return Response
                .status(Status.CREATED)
                .entity(QuadrinhoMapper.toResponseDTO(quadrinho))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response alterar(Long id, QuadrinhoRequestDTO dto) {
        service.update(id, QuadrinhoMapper.toEntity(dto));

        return Response.ok().build();
    }
}