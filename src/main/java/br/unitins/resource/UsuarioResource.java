package br.unitins.resource;

import java.util.List;

import br.unitins.service.interfaces.UsuarioService;
import br.unitins.dto.UsuarioRequestDTO;
import br.unitins.dto.UsuarioResponseDTO;
import br.unitins.mapper.UsuarioMapper;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
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

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed("admin")
public class UsuarioResource {

    @Inject
    UsuarioService service;

    @GET
    public Response buscarTodos() {
        List<UsuarioResponseDTO> lista = service.findAll()
                .stream()
                .map(UsuarioMapper::toResponseDTO)
                .toList();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        return Response.ok(UsuarioMapper.toResponseDTO(service.findById(id))).build();
    }

    @GET
    @Path("/nome/{nome}")
    public Response buscarPorNome(@PathParam("nome") String nome) {
        List<UsuarioResponseDTO> lista = service.findByNome(nome)
                .stream()
                .map(UsuarioMapper::toResponseDTO)
                .toList();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/login/{login}")
    public Response buscarPorLogin(@PathParam("login") String login) {
        return Response.ok(UsuarioMapper.toResponseDTO(service.findByLogin(login))).build();
    }

    @GET
    @Path("/email/{email}")
    public Response buscarPorEmail(@PathParam("email") String email) {
        return Response.ok(UsuarioMapper.toResponseDTO(service.findByEmail(email))).build();
    }

    @POST
    public Response incluir(@Valid UsuarioRequestDTO dto) {
        var criado = service.create(UsuarioMapper.toEntity(dto));
        return Response.status(Response.Status.CREATED)
                .entity(UsuarioMapper.toResponseDTO(criado))
                .build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("usuario")
    public Response alterar(@PathParam("id") Long id, @Valid UsuarioRequestDTO dto) {
        service.update(id, UsuarioMapper.toEntity(dto));
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
