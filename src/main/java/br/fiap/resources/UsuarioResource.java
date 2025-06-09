//package br.fiap.resources;
//
//import br.fiap.dtos.UsuarioCreateDTO;
//import br.fiap.dtos.UsuarioResponseDTO;
//import br.fiap.entities.Usuario;
//import br.fiap.repositories.UsuarioRepositoryBD;
//import br.fiap.services.LeituraNivelService;
//import jakarta.ws.rs.*;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Path("/usuarios")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//public class UsuarioResource {
//
//    private final UsuarioRepositoryBD repository = new UsuarioRepositoryBD();
//    private final LeituraNivelService leituraNivelService = new LeituraNivelService(); //
//
//    @POST
//    public Response cadastrarUsuario(UsuarioCreateDTO dto) {
//        try {
//            Usuario novoUsuario = new Usuario();
//            novoUsuario.setNome(dto.getNome());
//            novoUsuario.setEmail(dto.getEmail());
//            novoUsuario.setSenhaHash(dto.getSenha()); // Em produção, use hash!
//            novoUsuario.setTipoUsuario("comum");
//            novoUsuario.setCriadoEm(LocalDateTime.now());
//            novoUsuario.setCidade(dto.getCidade());
//            novoUsuario.setBairro(dto.getBairro());
//
//            repository.add(novoUsuario);
//
//            //  Geração automática de dados meteorológicos
//            leituraNivelService.gerarDadosMeteorologicos(novoUsuario);
//
//            UsuarioResponseDTO responseDTO = new UsuarioResponseDTO(
//                    novoUsuario.getId(),
//                    novoUsuario.getNome(),
//                    novoUsuario.getEmail(),
//                    novoUsuario.getTipoUsuario(),
//                    novoUsuario.getCidade(),
//                    novoUsuario.getBairro(),
//                    novoUsuario.getCriadoEm()
//            );
//
//            return Response.status(Response.Status.CREATED).entity(responseDTO).build();
//
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                    .entity("Erro ao cadastrar usuário: " + e.getMessage()).build();
//        }
//    }
//
//    @GET
//    public Response listarUsuarios() {
//        List<Usuario> usuarios = repository.getAllAtivos();
//        if (usuarios.isEmpty()) {
//            return Response.status(Response.Status.NOT_FOUND)
//                    .entity("Nenhum usuário encontrado.").build();
//        }
//
//        List<UsuarioResponseDTO> responseDTOs = usuarios.stream().map(usuario -> new UsuarioResponseDTO(
//                usuario.getId(),
//                usuario.getNome(),
//                usuario.getEmail(),
//                usuario.getTipoUsuario(),
//                usuario.getCidade(),
//                usuario.getBairro(),
//                usuario.getCriadoEm()
//        )).collect(Collectors.toList());
//
//        return Response.ok(responseDTOs).build();
//    }
//
//    @GET
//    @Path("/{id}")
//    public Response buscarPorId(@PathParam("id") int id) {
//        Optional<Usuario> usuarioOpt = repository.getById(id);
//        if (usuarioOpt.isEmpty()) {
//            return Response.status(Response.Status.NOT_FOUND)
//                    .entity("Usuário não encontrado com o ID: " + id).build();
//        }
//
//        Usuario usuario = usuarioOpt.get();
//        UsuarioResponseDTO dto = new UsuarioResponseDTO(
//                usuario.getId(),
//                usuario.getNome(),
//                usuario.getEmail(),
//                usuario.getTipoUsuario(),
//                usuario.getCidade(),
//                usuario.getBairro(),
//                usuario.getCriadoEm()
//        );
//
//        return Response.ok(dto).build();
//    }
//}


package br.fiap.resources;

import br.fiap.dtos.UsuarioCreateDTO;
import br.fiap.dtos.UsuarioLoginDTO;
import br.fiap.dtos.UsuarioResponseDTO;
import br.fiap.entities.Usuario;
import br.fiap.repositories.UsuarioRepositoryBD;
import br.fiap.services.LeituraNivelService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    private final UsuarioRepositoryBD repository = new UsuarioRepositoryBD();
    private final LeituraNivelService leituraNivelService = new LeituraNivelService();

    @POST
    public Response cadastrarUsuario(UsuarioCreateDTO dto) {
        try {
            Usuario novoUsuario = new Usuario();
            novoUsuario.setNome(dto.getNome());
            novoUsuario.setEmail(dto.getEmail());
            novoUsuario.setSenhaHash(dto.getSenha()); // Em produção, usar hash
            novoUsuario.setTipoUsuario("comum");
            novoUsuario.setCriadoEm(LocalDateTime.now());
            novoUsuario.setCidade(dto.getCidade());
            novoUsuario.setBairro(dto.getBairro());

            repository.add(novoUsuario);
            leituraNivelService.gerarDadosMeteorologicos(novoUsuario);

            UsuarioResponseDTO responseDTO = new UsuarioResponseDTO(
                    novoUsuario.getId(),
                    novoUsuario.getNome(),
                    novoUsuario.getEmail(),
                    novoUsuario.getTipoUsuario(),
                    novoUsuario.getCidade(),
                    novoUsuario.getBairro(),
                    novoUsuario.getCriadoEm()
            );

            return Response.status(Response.Status.CREATED).entity(responseDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar usuário: " + e.getMessage()).build();
        }
    }

    @POST
    @Path("/login")
    public Response login(UsuarioLoginDTO dto) {
        Optional<Usuario> usuarioOpt = repository.getByEmail(dto.getEmail());

        if (usuarioOpt.isEmpty() ||
                !usuarioOpt.get().getSenhaHash().equals(dto.getSenha())) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Email ou senha inválidos").build();
        }

        Usuario usuario = usuarioOpt.get();

        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTipoUsuario(),
                usuario.getCidade(),
                usuario.getBairro(),
                usuario.getCriadoEm()
        );

        return Response.ok(responseDTO).build();
    }

    @GET
    public Response listarUsuarios() {
        List<Usuario> usuarios = repository.getAllAtivos();
        if (usuarios.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhum usuário encontrado.").build();
        }

        List<UsuarioResponseDTO> responseDTOs = usuarios.stream().map(usuario -> new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTipoUsuario(),
                usuario.getCidade(),
                usuario.getBairro(),
                usuario.getCriadoEm()
        )).collect(Collectors.toList());

        return Response.ok(responseDTOs).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        Optional<Usuario> usuarioOpt = repository.getById(id);
        if (usuarioOpt.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuário não encontrado com o ID: " + id).build();
        }

        Usuario usuario = usuarioOpt.get();
        UsuarioResponseDTO dto = new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTipoUsuario(),
                usuario.getCidade(),
                usuario.getBairro(),
                usuario.getCriadoEm()
        );

        return Response.ok(dto).build();
    }
}
