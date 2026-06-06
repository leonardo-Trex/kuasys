package br.unitins.resource;

import br.unitins.service.interfaces.EnderecoService;
import br.unitins.dto.EnderecoRequestDTO;
import br.unitins.dto.EnderecoResponseDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import java.util.List;

@Path("/usuarios/me/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    @Inject
    EnderecoService enderecoService;

    @Inject
    JsonWebToken jwt;

    @GET
    @RolesAllowed({"usuario", "customer"})
    public Response listarEnderecosDoCliente() {
        String keycloakId = jwt.getSubject();

        List<EnderecoResponseDTO> enderecos = enderecoService.listarEnderecosDoCliente(keycloakId);

        return Response.ok(enderecos).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"usuario", "customer"})
    public Response buscarPorIdECliente(@PathParam("id") Long id) {
        String keycloakId = jwt.getSubject();

        EnderecoResponseDTO endereco = enderecoService.buscarPorIdECliente(id, keycloakId);

        return Response.ok(endereco).build();
    }

    @POST
    @RolesAllowed({"usuario", "customer"})
    public Response salvar(@Valid EnderecoRequestDTO dto) {
        String keycloakId = jwt.getSubject();

        EnderecoResponseDTO endereco = enderecoService.salvar(keycloakId, dto);

        return Response.status(Response.Status.CREATED).entity(endereco).build();
    }
}
