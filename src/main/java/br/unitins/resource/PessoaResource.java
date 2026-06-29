package br.unitins.resource;

import br.unitins.dto.PessoaRequestDTO;
import br.unitins.dto.PessoaResponseDTO;
import br.unitins.mapper.PessoaMapper;
import br.unitins.model.Quadrinista;
import br.unitins.service.interfaces.PessoaService;
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
    PessoaService service;

    @GET
    public Response buscarTodo() {
        List<PessoaResponseDTO> lista = service.findAll()
                .stream()
                .map(PessoaMapper::toResponseDTO)
                .toList();

        return Response.ok(lista).build();
    }

    @GET
//    @Path("/{id}")
    public Response buscarPeloId(@PathParam("id") Long id) {
        return Response.ok(PessoaMapper.toResponseDTO(service.findById(id))).build();
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
    public Response incluir(@Valid PessoaRequestDTO dto) {
        Quadrinista quadrinista = service.create(PessoaMapper.toEntity(dto));

        return Response
                .status(Status.CREATED)
                .entity(PessoaMapper.toResponseDTO(quadrinista))
                .build();
    }

    @PUT
    @Path("/{id}")
//    @RolesAllowed("admin")
    public Response alterar(@PathParam("id") Long id, PessoaRequestDTO dto) {
        service.update(id, PessoaMapper.toEntity(dto));

        return Response.ok().build();
    }
}
