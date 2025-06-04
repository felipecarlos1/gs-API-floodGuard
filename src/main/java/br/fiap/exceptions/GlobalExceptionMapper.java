package br.fiap.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        Map<String, String> erro = new HashMap<>();
        erro.put("mensagem", exception.getMessage());

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(erro)
                .build();
    }
}
