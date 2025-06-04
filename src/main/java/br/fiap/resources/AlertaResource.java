package br.fiap.resources;

import br.fiap.entities.Alerta;
import br.fiap.repositories.AlertaRepositoryBD;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/alertas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlertaResource {

    private final AlertaRepositoryBD repository = new AlertaRepositoryBD();

    // POST - Cadastrar novo alerta
    @POST
    public Response cadastrarAlerta(Alerta alerta) {
        try {
            repository.add(alerta);
            return Response.status(Response.Status.CREATED).entity(alerta).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar alerta: " + e.getMessage()).build();
        }
    }

    // GET - Listar alertas ativos
    @GET
    public Response listarAlertas() {
        try {
            List<Alerta> alertas = repository.getAllAtivos();
            if (alertas.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Nenhum alerta encontrado").build();
            }
            return Response.ok(alertas).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar alertas: " + e.getMessage()).build();
        }
    }

    // GET - Buscar alerta por ID
    @GET
    @Path("/{id}")
    public Response buscarAlertaPorId(@PathParam("id") int id) {
        try {
            Optional<Alerta> alerta = repository.getById(id);
            if (alerta.isPresent()) {
                return Response.ok(alerta.get()).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Alerta com ID " + id + " não encontrado").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar alerta: " + e.getMessage()).build();
        }
    }
}


