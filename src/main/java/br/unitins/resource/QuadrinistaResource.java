package br.unitins.resource;

import br.unitins.dto.quadrinista.QuadrinistaCreateDTO;
import br.unitins.dto.quadrinista.QuadrinistaResponseDTO;
import br.unitins.mapper.QuadrinistaMapper;
import br.unitins.model.Quadrinista;
import br.unitins.service.interfaces.QuadrinistaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.List;

@Path("/pessoas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@Authenticated
public class PessoaResource {

    @Inject
    QuadrinistaService service;

    @GET
    public Response buscarTodo() {
        List<QuadrinistaResponseDTO> lista = service.findAll()
                .stream()
                .map(QuadrinistaMapper::toResponseDTO)
                .toList();

        return Response.ok(lista).build();
    }

    @GET
//    @Path("/{id}")
    public Response buscarPeloId(@PathParam("id") Long id) {
        return Response.ok(QuadrinistaMapper.toResponseDTO(service.findById(id))).build();
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
    public Response incluir(@Valid QuadrinistaCreateDTO dto) {
        Quadrinista quadrinista = service.create(QuadrinistaMapper.toEntity(dto));

        return Response
                .status(Status.CREATED)
                .entity(QuadrinistaMapper.toResponseDTO(quadrinista))
                .build();
    }

    @PUT
    @Path("/{id}")
//    @RolesAllowed("admin")
    public Response alterar(@PathParam("id") Long id, QuadrinistaCreateDTO dto) {
        service.update(id, QuadrinistaMapper.toEntity(dto));

        return Response.ok().build();
    }
}
