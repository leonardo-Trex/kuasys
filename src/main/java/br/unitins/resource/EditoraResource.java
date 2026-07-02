package br.unitins.resource;

import br.unitins.dto.editora.EditoraCreateDTO;
import br.unitins.dto.editora.EditoraResponseDTO;
import br.unitins.mapper.EditoraMapper;
import br.unitins.model.Editora;
import br.unitins.service.interfaces.EditoraService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.List;

@Path("/editoras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@PermitAll
public class EditoraResource {

    @Inject
    EditoraService service;

    @GET
    public Response buscarTodo() {
        List<EditoraResponseDTO> lista = service.findAll()
                .stream()
                .map(EditoraMapper::toResponseDTO)
                .toList();

        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPeloId(@PathParam("id") Long id) {
        return Response.ok(EditoraMapper.toResponseDTO(service.findById(id))).build();
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
    public Response incluir(@Valid EditoraCreateDTO dto) {
        Editora editora = service.create(EditoraMapper.toEntity(dto));

        return Response
                .status(Status.CREATED)
                .entity(EditoraMapper.toResponseDTO(editora))
                .build();
    }

    @PUT
    @Path("/{id}")
//    @RolesAllowed("admin")
    public Response alterar(@PathParam("id") Long id, EditoraCreateDTO dto) {
        service.update(id, EditoraMapper.toEntity(dto));

        return Response.ok().build();
    }
}
