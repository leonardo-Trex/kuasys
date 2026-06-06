//package br.unitins.resource;
//
//import br.unitins.service.interfaces.PedidoService;
//import br.unitins.dto.PedidoResponseDTO;
//import jakarta.annotation.security.RolesAllowed;
//import jakarta.inject.Inject;
//import jakarta.ws.rs.POST;
//import jakarta.ws.rs.Path;
//import jakarta.ws.rs.Produces;
//import jakarta.ws.rs.core.Response;
//import org.eclipse.microprofile.jwt.JsonWebToken;
//
//@Path("/pedidos")
//@Produces("application/json")
//public class PedidoResource {
//
//    @Inject
//    PedidoService pedidoService;
//
//    @Inject
//    JsonWebToken jwt;
//
//    @POST
//    @RolesAllowed({"usuario", "customer"})
//    public Response finalizarCompra() {
//        String usuarioId = jwt.getSubject();
//        PedidoResponseDTO pedido = pedidoService.finalizarCompra(usuarioId);
//        return Response.status(Response.Status.CREATED).entity(pedido).build();
//    }
//}
