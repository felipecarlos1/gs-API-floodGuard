package br.fiap.repositories;

import br.fiap.entities.Usuario;
import br.fiap.infrastructure.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepositoryBD implements _CrudRepository<Usuario> {

    private Connection connection;

    public UsuarioRepositoryBD() {
        try {
            this.connection = DatabaseConfig.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }

    @Override
    public void add(Usuario usuario) {
        String sql = "INSERT INTO T_FG_USUARIOS (id, nome, email, senha_hash, tipo_usuario, criado_em, cidade, bairro) " +
                "VALUES (seq_usuarios.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenhaHash());
            stmt.setString(4, usuario.getTipoUsuario());
            stmt.setTimestamp(5, Timestamp.valueOf(usuario.getCriadoEm()));
            stmt.setString(6, usuario.getCidade());
            stmt.setString(7, usuario.getBairro());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    @Override
    public boolean remove(Usuario usuario) {
        return removeById(usuario.getId());
    }

    @Override
    public boolean removeById(int id) {
        String sql = "DELETE FROM T_FG_USUARIOS WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover usuário: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Usuario usuario) {
        return deleteById(usuario.getId());
    }

    @Override
    public boolean deleteById(int id) {
        // Exclusão lógica ainda não implementada
        return false;
    }

    @Override
    public boolean update(Usuario usuario) {
        String sql = "UPDATE T_FG_USUARIOS SET nome = ?, email = ?, senha_hash = ?, tipo_usuario = ?, cidade = ?, bairro = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenhaHash());
            stmt.setString(4, usuario.getTipoUsuario());
            stmt.setString(5, usuario.getCidade());
            stmt.setString(6, usuario.getBairro());
            stmt.setInt(7, usuario.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    @Override
    public List<Usuario> getAll() {
        String sql = "SELECT * FROM T_FG_USUARIOS";
        List<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os usuários: " + e.getMessage());
        }
        return usuarios;
    }

    @Override
    public List<Usuario> getAllAtivos() {
        return getAll();
    }

    @Override
    public List<Usuario> get() {
        return getAll();
    }

    @Override
    public Optional<Usuario> getById(int id) {
        String sql = "SELECT * FROM T_FG_USUARIOS WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> getByIdIncludingDeleted(int id) {
        return getById(id);
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM T_FG_USUARIOS WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência do usuário: " + e.getMessage());
        }
        return false;
    }

    private Usuario map(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getInt("id"));
        u.setNome(rs.getString("nome"));
        u.setEmail(rs.getString("email"));
        u.setSenhaHash(rs.getString("senha_hash"));
        u.setTipoUsuario(rs.getString("tipo_usuario"));
        u.setCriadoEm(rs.getTimestamp("criado_em").toLocalDateTime());
        u.setCidade(rs.getString("cidade"));
        u.setBairro(rs.getString("bairro"));
        return u;
    }
}
