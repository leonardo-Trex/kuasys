package br.unitins.resource;

import br.unitins.dto.quadrinho.QuadrinhoCreateDTO;
import br.unitins.dto.quadrinho.QuadrinhoResponseDTO;
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
    public Response buscarTodos() {

        List<QuadrinhoResponseDTO> lista = service.findAll();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPeloId(@PathParam("id") Long id) {

        return Response.ok(service.findById(id)).build();
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
    public Response incluir(@Valid QuadrinhoCreateDTO dto) {

        QuadrinhoResponseDTO q = service.create(dto);

        return Response
                .status(Status.CREATED)
                .entity(q)
                .build();
    }

//    @PUT
//    @Path("/{id}")
////    @RolesAllowed("admin")
//    public Response alterar(@PathParam("id") Long id, QuadrinhoCreateDTO dto) {
//        service.update(id, QuadrinhoMapper.toEntity(dto));
//
//        return Response.ok().build();
//    }
}