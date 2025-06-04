package br.fiap.resources;

import br.fiap.entities.LeituraNivel;
import br.fiap.repositories.LeituraNivelRepositoryBD;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/leituras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LeituraNivelResource {

    private final LeituraNivelRepositoryBD repository = new LeituraNivelRepositoryBD();

    // POST - Cadastrar uma nova leitura de nível
    @POST
    public Response cadastrarLeitura(LeituraNivel leitura) {
        try {
            repository.add(leitura);
            return Response.status(Response.Status.CREATED).entity(leitura).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar leitura de nível: " + e.getMessage()).build();
        }
    }

    // GET - Listar todas as leituras ativas
    @GET
    public Response listarLeituras() {
        try {
            List<LeituraNivel> leituras = repository.getAllAtivos();
            if (leituras.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("Nenhuma leitura encontrada").build();
            }
            return Response.ok(leituras).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar leituras: " + e.getMessage()).build();
        }
    }

    // GET - Buscar leitura específica por ID
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            Optional<LeituraNivel> leitura = repository.getById(id);
            if (leitura.isPresent()) {
                return Response.ok(leitura.get()).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Leitura com ID " + id + " não encontrada").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar leitura: " + e.getMessage()).build();
        }
    }
}
