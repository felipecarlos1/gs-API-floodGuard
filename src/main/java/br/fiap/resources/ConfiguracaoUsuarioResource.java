package br.fiap.resources;

import br.fiap.entities.ConfiguracaoUsuario;
import br.fiap.repositories.ConfiguracaoUsuarioRepositoryBD;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/configuracoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConfiguracaoUsuarioResource {

    private final ConfiguracaoUsuarioRepositoryBD repository = new ConfiguracaoUsuarioRepositoryBD();

    // POST - Cadastrar configuração
    @POST
    public Response cadastrarConfiguracao(ConfiguracaoUsuario configuracao) {
        try {
            repository.add(configuracao);
            return Response.status(Response.Status.CREATED).entity(configuracao).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar configuração: " + e.getMessage()).build();
        }
    }

    // GET - Listar todas configurações ativas
    @GET
    public Response listarConfiguracoes() {
        try {
            List<ConfiguracaoUsuario> lista = repository.getAllAtivos();
            if (lista.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Nenhuma configuração encontrada").build();
            }
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar configurações: " + e.getMessage()).build();
        }
    }

    // GET - Buscar por ID
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            Optional<ConfiguracaoUsuario> config = repository.getById(id);
            if (config.isPresent()) {
                return Response.ok(config.get()).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Configuração não encontrada para o ID " + id).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar configuração: " + e.getMessage()).build();
        }
    }
}

