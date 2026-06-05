// package br.unitins.resource;

// import java.util.List;

// import br.unitins.Service.interfaces.EdicaoService;
// import br.unitins.dto.ProdutoResponseDTO;
// import br.unitins.mapper.ProdutoMapper;
// import jakarta.annotation.security.PermitAll;
// import jakarta.inject.Inject;
// import jakarta.ws.rs.GET;
// import jakarta.ws.rs.Path;
// import jakarta.ws.rs.Produces;
// import jakarta.ws.rs.core.MediaType;
// import jakarta.ws.rs.core.Response;

// @Path("/vitrine")
// @Produces(MediaType.APPLICATION_JSON)
// @PermitAll
// public class VitrineResource {

// @Inject
// EdicaoService service;

// @GET
// @Path("/quadrinhos")
// public Response listarTodos() {
// List<ProdutoResponseDTO> lista = service.findAll()
// .stream()
// .map(ProdutoMapper::toResponseDTO)
// .toList();

// return Response.ok(lista).build();
// }
// }
