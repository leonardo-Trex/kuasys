package br.unitins.resource;

import br.unitins.dto.edicao.EdicaoCreateDTO;
import br.unitins.dto.edicao.EdicaoResponseDTO;
import br.unitins.mapper.EdicaoMapper;
import br.unitins.model.Edicao;
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

    //sdflknçlknfaslkn
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
//    @RolesAllowed("admin")
    public Response deletar(@PathParam("id") Long id) {
        service.delete(id);

        return Response.noContent().build();
    }

    @POST
//    @RolesAllowed("admin")
    public Response incluir(@Valid EdicaoCreateDTO dto) {
        Edicao edicao = service.create(dto);

        return Response
                .status(Status.CREATED)
                .entity(EdicaoMapper.toResponseDTO(edicao))
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
