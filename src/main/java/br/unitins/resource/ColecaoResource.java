package br.unitins.resource;

import java.util.List;

import br.unitins.service.interfaces.ColecaoService;
import br.unitins.dto.ColecaoRequestDTO;
import br.unitins.dto.ColecaoResponseDTO;
import br.unitins.mapper.ColecaoMapper;
import br.unitins.model.Colecao;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
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

@Path("/colecoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@PermitAll
public class ColecaoResource {

    @Inject
    ColecaoService service;

    @GET
//    @RolesAllowed("usuario")
    public Response buscarTodo() {
        List<ColecaoResponseDTO> lista = service.findAll()
                .stream()
                .map(ColecaoMapper::toResponseDTO)
                .toList();

        return Response.ok(lista).build();
    }

    // public Response buscarPeloNome() {

    // }

    @GET
    @Path("/{id}")
//    @RolesAllowed("usuario")
    public Response buscarPeloId(@PathParam("id") Long id) {
        return Response.ok(ColecaoMapper.toResponseDTO(service.findById(id))).build();
    }

    @DELETE
    @Path("/{id}")
//    @RolesAllowed("usuario")
    public Response deletar(@PathParam("id") Long id) {
        service.delete(id);

        return Response.noContent().build();
    }

    @POST
//    @RolesAllowed("usuario")
    public Response incluir(@Valid ColecaoRequestDTO dto) {
        Colecao colecao = service.create(ColecaoMapper.toEntity(dto));

        return Response
                .status(Status.CREATED)
                .entity(ColecaoMapper.toResponseDTO(colecao))
                .build();
    }

    @PUT
    @Path("/{id}")
//    @RolesAllowed("usuario")
    public Response alterar(@PathParam("id") Long id, ColecaoRequestDTO dto) {
        service.update(id, ColecaoMapper.toEntity(dto));

        return Response.ok().build();
    }
}
