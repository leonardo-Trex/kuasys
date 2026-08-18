package br.unitins.resource;

import br.unitins.dto.endereco.EnderecoCreateDTO;
import br.unitins.dto.endereco.EnderecoResponseDTO;
import br.unitins.service.interfaces.EnderecoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.List;

@Path("/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    @Inject
    EnderecoService service;

    @GET
    public Response buscarTodos() {
        List<EnderecoResponseDTO> enderecos = service.findAll();

        return Response
                .ok(enderecos)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPeloId(@PathParam("id") Long id) {
        EnderecoResponseDTO endereco = service.findById(id);

        return Response
                .ok(endereco)
                .build();
    }

    @GET
    @Path("/usuario/{usuarioId}")
    public Response buscarPeloUsuarioId(@PathParam("usuarioId") Long usuarioId) {
        List<EnderecoResponseDTO> enderecos = service.findByUsuarioId(usuarioId);

        return Response
                .ok(enderecos)
                .build();
    }

    @POST
    public Response incluir(@Valid EnderecoCreateDTO dto) {
        EnderecoResponseDTO endereco = service.create(dto);

        return Response
                .status(Status.CREATED)
                .entity(endereco)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response alterar(@PathParam("id") Long id, @Valid EnderecoCreateDTO dto) {
        service.update(id, dto);

        return Response
                .ok()
                .build();
    }
}
