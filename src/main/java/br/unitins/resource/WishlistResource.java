package br.unitins.resource;

import java.util.List;

import br.unitins.Service.interfaces.WishlistService;
import br.unitins.model.Edicao;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/api/wishlist")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WishlistResource {

    @Inject
    WishlistService wishlistService;

    @Inject
    JsonWebToken jwt;

    @GET
    public Response listar() {
        String keycloakId = jwt.getSubject();
        List<Edicao> wishlist = wishlistService.listar(keycloakId);
        return Response.ok(wishlist).build();
    }

    @POST
    @Path("/{produtoId}")
    public Response adicionar(@PathParam("produtoId") Long produtoId) {
        String keycloakId = jwt.getSubject();
        wishlistService.adicionar(keycloakId, produtoId);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{produtoId}")
    public Response remover(@PathParam("produtoId") Long produtoId) {
        String keycloakId = jwt.getSubject();
        wishlistService.remover(keycloakId, produtoId);
        return Response.noContent().build();
    }
}
