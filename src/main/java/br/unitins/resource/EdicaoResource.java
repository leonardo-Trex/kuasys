package br.unitins.resource;

import java.util.List;

import br.unitins.Service.interfaces.EdicaoService;
import br.unitins.dto.EdicaoRequestDTO;
import br.unitins.dto.EdicaoResponseDTO;
import br.unitins.mapper.EdicaoMapper;
import br.unitins.model.Edicao;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/edicoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PermitAll
public class EdicaoResource {

    @Inject
    EdicaoService service;

    @GET
    public Response buscarTodo(@QueryParam("nome") String nome) {
        if (nome != null && !nome.isEmpty()) {
            List<EdicaoResponseDTO> lista = service.findByNome(nome)
                    .stream()
                    .map(EdicaoMapper::toResponseDTO)
                    .toList();
            return Response.ok(lista).build();
        }

        List<EdicaoResponseDTO> listaCompleta = service.findAll()
                .stream()
                .map(EdicaoMapper::toResponseDTO)
                .toList();

        return Response.ok(listaCompleta).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPeloId(@PathParam("id") Long id) {
        return Response.ok(EdicaoMapper.toResponseDTO(service.findById(id))).build();
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
    public Response incluir(@Valid EdicaoRequestDTO dto) {
        Edicao edicao = service.create(dto);

        return Response
                .status(Status.CREATED)
                .entity(EdicaoMapper.toResponseDTO(edicao))
                .build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response alterar(@PathParam("id") Long id, @Valid EdicaoRequestDTO dto) {
        service.update(id, dto);

        return Response.ok().build();
    }
}
