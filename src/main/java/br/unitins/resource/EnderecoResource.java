package br.unitins.resource;

import br.unitins.Service.interfaces.EnderecoService;
import br.unitins.dto.EnderecoResponseDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import java.util.List;

@Path("/usuarios/me/enderecos")
@Produces(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    @Inject
    EnderecoService enderecoService;

    @Inject
    JsonWebToken jwt;

    @GET
    @RolesAllowed({"usuario", "customer"})
    public Response listarEnderecosDoCliente() {
        String usuarioId = jwt.getSubject();
        
        // Converter UUID (string) do JWT para Long
        Long usuarioIdLong = Long.parseLong(usuarioId);
        
        List<EnderecoResponseDTO> enderecos = enderecoService.listarEnderecosDoCliente(usuarioIdLong);
        
        return Response.ok(enderecos).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"usuario", "customer"})
    public Response buscarPorIdECliente(@PathParam("id") Long id) {
        String usuarioId = jwt.getSubject();
        
        // Converter UUID (string) do JWT para Long
        Long usuarioIdLong = Long.parseLong(usuarioId);
        
        EnderecoResponseDTO endereco = enderecoService.buscarPorIdECliente(id, usuarioIdLong);
        
        return Response.ok(endereco).build();
    }
}
