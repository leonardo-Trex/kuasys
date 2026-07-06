package br.unitins.resource;

import br.unitins.dto.pedido.PedidoCreateDTO;
import br.unitins.service.interfaces.PedidoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/pedidos")
@Produces("application/json")
public class PedidoResource {

    @Inject
    PedidoService pedidoService;

//    @Inject
//    JsonWebToken jwt;

    @POST
//    @RolesAllowed({"usuario", "customer"})
    public Response finalizarCompra(PedidoCreateDTO dto) {
//        String usuarioId = jwt.getSubject();
//        PedidoResponseDTO pedido = pedidoService.finalizarCompra(usuarioId);
        
        return Response.status(Response.Status.CREATED).build();
    }
}
