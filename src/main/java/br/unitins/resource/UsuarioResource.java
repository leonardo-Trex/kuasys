package br.unitins.resource;

import br.unitins.dto.usuario.UsuarioCreateDTO;
import br.unitins.dto.usuario.UsuarioResponseDTO;
import br.unitins.service.interfaces.UsuarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RolesAllowed("admin")
public class UsuarioResource {

    @Inject
    UsuarioService service;

    @GET
    public Response buscarTodos() {

        List<UsuarioResponseDTO> lista = service.findAll();
        return Response
                .ok(lista)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {

        return Response
                .ok(service.findById(id))
                .build();
    }

    @GET
    @Path("/nome/{nome}")
    public Response buscarPorNome(@PathParam("nome") String nome) {

        List<UsuarioResponseDTO> lista = service.findByNome(nome);
        return Response
                .ok(lista)
                .build();
    }

    @GET
    @Path("/login/{login}")
    public Response buscarPorLogin(@PathParam("login") String login) {

        return Response.ok(service.findByLogin(login)).build();
    }

    @GET
    @Path("/email/{email}")
    public Response buscarPorEmail(@PathParam("email") String email) {

        return Response
                .ok(service.findByEmail(email))
                .build();
    }

    @POST
//    TODO: validação aqui
    public Response incluir(UsuarioCreateDTO dto) {

        UsuarioResponseDTO criado = service.create(dto);
        return Response.status(Response.Status.CREATED)
                .entity(criado)
                .build();
    }

//    @PUT
//    @Path("/{id}")
//    @RolesAllowed("usuario") Sem roles ainda
//    Validação aqui também
//    Esse aqui precisa ficar um pouco de lado por enquanto
//    public Response alterar(@PathParam("id") Long id, UsuarioCreateDTO dto) {
//        service.update(id, UsuarioMapper.toEntity(dto));
//        return Response.ok().build();
//    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {

        service.delete(id);
        return Response
                .noContent()
                .build();
    }
}
