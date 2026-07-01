package br.unitins.resource;

import br.unitins.dto.colecao.ColecaoRequestDTO;
import br.unitins.dto.colecao.ColecaoResponseDTO;
import br.unitins.mapper.ColecaoMapper;
import br.unitins.model.Colecao;
import br.unitins.service.interfaces.ColecaoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.List;

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
