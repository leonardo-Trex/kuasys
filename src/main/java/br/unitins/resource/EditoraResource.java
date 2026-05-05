package br.unitins.resource;

import java.util.List;

import br.unitins.Service.interfaces.EditoraService;
import br.unitins.dto.EditoraRequestDTO;
import br.unitins.dto.EditoraResponseDTO;
import br.unitins.mapper.EditoraMapper;
import br.unitins.model.Editora;
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

@Path("/editoras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
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
    public Response buscarPeloId(Long id) {
        return Response.ok(EditoraMapper.toResponseDTO(service.findById(id))).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(Long id) {
        service.delete(id);

        return Response.noContent().build();
    }

    @POST
    public Response incluir(@Valid EditoraRequestDTO dto) {
        Editora editora = service.create(EditoraMapper.toEntity(dto));

        return Response
                .status(Status.CREATED)
                .entity(EditoraMapper.toResponseDTO(editora))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response alterar(Long id, EditoraRequestDTO dto) {
        service.update(id, EditoraMapper.toEntity(dto));

        return Response.ok().build();
    }
}
