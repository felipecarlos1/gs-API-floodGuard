package br.fiap.repositories;

import br.fiap.entities.ConfiguracaoUsuario;
import br.fiap.infrastructure.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConfiguracaoUsuarioRepositoryBD implements _CrudRepository<ConfiguracaoUsuario> {

    private Connection connection;

    public ConfiguracaoUsuarioRepositoryBD() {
        try {
            this.connection = DatabaseConfig.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados", e);
        }
    }

    @Override
    public void add(ConfiguracaoUsuario config) {
        String sql = "INSERT INTO T_FG_CONFIGURACAO_USUARIO (id, usuario_id, receber_alertas_email, idioma) " +
                "VALUES (seq_configuracao_usuario.NEXTVAL, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, config.getUsuarioId());
            stmt.setString(2, config.isReceberAlertasEmail() ? "Y" : "N");
            stmt.setString(3, config.getIdioma());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar configuração de usuário", e);
        }
    }

    @Override
    public boolean remove(ConfiguracaoUsuario config) {
        return removeById(config.getId());
    }

    @Override
    public boolean removeById(int id) {
        String sql = "DELETE FROM T_FG_CONFIGURACAO_USUARIO WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover configuração de usuário", e);
        }
    }

    @Override
    public boolean delete(ConfiguracaoUsuario config) {
        return false; // Exclusão lógica não implementada
    }

    @Override
    public boolean deleteById(int id) {
        return false; // Exclusão lógica não implementada
    }

    @Override
    public boolean update(ConfiguracaoUsuario config) {
        String sql = "UPDATE T_FG_CONFIGURACAO_USUARIO SET usuario_id = ?, receber_alertas_email = ?, idioma = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, config.getUsuarioId());
            stmt.setString(2, config.isReceberAlertasEmail() ? "Y" : "N");
            stmt.setString(3, config.getIdioma());
            stmt.setInt(4, config.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar configuração de usuário", e);
        }
    }

    @Override
    public List<ConfiguracaoUsuario> getAll() {
        List<ConfiguracaoUsuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_FG_CONFIGURACAO_USUARIO";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar configurações", e);
        }
        return lista;
    }

    @Override
    public List<ConfiguracaoUsuario> getAllAtivos() {
        return getAll(); // Não há exclusão lógica
    }

    @Override
    public List<ConfiguracaoUsuario> get() {
        return getAll();
    }

    @Override
    public Optional<ConfiguracaoUsuario> getById(int id) {
        String sql = "SELECT * FROM T_FG_CONFIGURACAO_USUARIO WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar configuração por ID", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ConfiguracaoUsuario> getByIdIncludingDeleted(int id) {
        return getById(id);
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM T_FG_CONFIGURACAO_USUARIO WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência de configuração", e);
        }
    }

    private ConfiguracaoUsuario map(ResultSet rs) throws SQLException {
        return new ConfiguracaoUsuario(
                rs.getInt("id"),
                rs.getInt("usuario_id"),
                "Y".equalsIgnoreCase(rs.getString("receber_alertas_email")),
                rs.getString("idioma")
        );
    }
}


