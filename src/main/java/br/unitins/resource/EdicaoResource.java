package br.unitins.resource;

import java.util.List;

import br.unitins.Service.interfaces.EdicaoService;
import br.unitins.model.Edicao;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/edicoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EdicaoResource {

    @Inject
    EdicaoService service;

    @GET
    public Response buscarTodo() {
        List<EdicaoResponseDTO> lista = service.findAll()
                .stream()
                .map(EdicaoMapper::toResponseDTO)
                .toList();
        return Response.ok(lista).build();
    }
}
