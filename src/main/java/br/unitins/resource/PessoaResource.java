package br.unitins.resource;

import java.util.List;

import br.unitins.Service.interfaces.PessoaService;
import br.unitins.dto.PessoaRequestDTO;
import br.unitins.dto.PessoaResponseDTO;
import br.unitins.mapper.PessoaMapper;
import br.unitins.model.Pessoa;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.annotation.security.RolesAllowed;
import io.quarkus.security.Authenticated;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/pessoas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
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
    @Path("/{id}")
    public Response buscarPeloId(@PathParam("id") Long id) {
        return Response.ok(PessoaMapper.toResponseDTO(service.findById(id))).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response deletar(@PathParam("id") Long id) {
        service.delete(id);

        return Response.noContent().build();
    }

    @POST
    @RolesAllowed("admin")
    public Response incluir(@Valid PessoaRequestDTO dto) {
        Pessoa pessoa = service.create(PessoaMapper.toEntity(dto));

        return Response
                .status(Status.CREATED)
                .entity(PessoaMapper.toResponseDTO(pessoa))
                .build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response alterar(@PathParam("id") Long id, PessoaRequestDTO dto) {
        service.update(id, PessoaMapper.toEntity(dto));

        return Response.ok().build();
    }
}
