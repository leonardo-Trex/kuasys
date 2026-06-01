package br.unitins.resource;

import br.unitins.Service.PedidoService;
import br.unitins.model.Pedido;
import jakarta.annotation.Nullable;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoService pedidoService;

    @Inject
    @Nullable
    JsonWebToken jwt;

    @POST
    @Path("/checkout")
    public Response checkout(@HeaderParam("X-Cart-Token") String cartToken) {
        String usuarioId = null;
        if (jwt != null && jwt.getRawToken() != null) {
            usuarioId = jwt.getSubject();
        }

        Pedido pedido = pedidoService.criarPedidoAPartirDoCarrinho(cartToken, usuarioId);
        return Response.status(Response.Status.CREATED).entity(pedido).build();
    }
}
