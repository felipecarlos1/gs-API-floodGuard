package br.fiap.resources;

import br.fiap.entities.HistoricoEnchente;
import br.fiap.repositories.HistoricoEnchenteRepositoryBD;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/historico-enchentes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HistoricoEnchenteResource {

    private final HistoricoEnchenteRepositoryBD repository = new HistoricoEnchenteRepositoryBD();

    // POST - Cadastrar novo histórico
    @POST
    public Response cadastrarHistorico(HistoricoEnchente historico) {
        try {
            repository.add(historico);
            return Response.status(Response.Status.CREATED).entity(historico).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar histórico: " + e.getMessage()).build();
        }
    }

    // GET - Listar históricos ativos
    @GET
    public Response listarHistoricos() {
        try {
            List<HistoricoEnchente> historicos = repository.getAllAtivos();
            if (historicos.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Nenhum histórico encontrado").build();
            }
            return Response.ok(historicos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar históricos: " + e.getMessage()).build();
        }
    }

    // GET - Buscar histórico por ID
    @GET
    @Path("/{id}")
    public Response buscarHistoricoPorId(@PathParam("id") int id) {
        try {
            Optional<HistoricoEnchente> historico = repository.getById(id);
            if (historico.isPresent()) {
                return Response.ok(historico.get()).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Histórico com ID " + id + " não encontrado").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar histórico: " + e.getMessage()).build();
        }
    }
}

