package br.unitins.resource;

import br.unitins.dto.edicao.EdicaoCreateDTO;
import br.unitins.dto.edicao.EdicaoResponseDTO;
import br.unitins.service.interfaces.EdicaoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.List;

@Path("/edicoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@PermitAll
public class EdicaoResource {

    @Inject
    EdicaoService service;

    @GET
    public Response buscarTodos() {

        List<EdicaoResponseDTO> lista = service.findAll();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPeloId(@PathParam("id") Long id) {

        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/buscar")
    public Response buscarPeloNome(@QueryParam("nome") String nome) {

        return Response
                .ok()
                .entity(service.findByNome(nome))
                .build();
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
    public Response incluir(@Valid EdicaoCreateDTO dto) {
        EdicaoResponseDTO edicao = service.create(dto);

        return Response
                .status(Status.CREATED)
                .entity(edicao)
                .build();
    }

    @PUT
    @Path("/{id}")
//    @RolesAllowed("admin")
    public Response alterar(@PathParam("id") Long id, @Valid EdicaoCreateDTO dto) {

        service.update(id, dto);

        return Response.ok().build();
    }
}
