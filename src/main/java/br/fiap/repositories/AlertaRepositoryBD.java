package br.fiap.repositories;

import br.fiap.entities.Alerta;
import br.fiap.infrastructure.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlertaRepositoryBD implements _CrudRepository<Alerta> {

    private Connection connection;

    public AlertaRepositoryBD() {
        try {
            this.connection = DatabaseConfig.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados", e);
        }
    }

    @Override
    public void add(Alerta alerta) {
        String sql = "INSERT INTO T_FG_ALERTAS (id, tipo_alerta, descricao, data_hora, localizacao, gravidade, gerado_por) " +
                "VALUES (seq_alertas.NEXTVAL, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, alerta.getTipoAlerta());
            stmt.setString(2, alerta.getDescricao());
            stmt.setTimestamp(3, Timestamp.valueOf(alerta.getDataHora()));
            stmt.setString(4, alerta.getLocalizacao());
            stmt.setString(5, alerta.getGravidade());
            stmt.setInt(6, alerta.getGeradoPor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir alerta", e);
        }
    }

    @Override
    public boolean remove(Alerta alerta) {
        return removeById(alerta.getId());
    }

    @Override
    public boolean removeById(int id) {
        String sql = "DELETE FROM T_FG_ALERTAS WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover alerta", e);
        }
    }

    @Override
    public boolean delete(Alerta alerta) {
        return false; // Exclusão lógica não implementada
    }

    @Override
    public boolean deleteById(int id) {
        return false; // Exclusão lógica não implementada
    }

    @Override
    public boolean update(Alerta alerta) {
        String sql = "UPDATE T_FG_ALERTAS SET tipo_alerta = ?, descricao = ?, data_hora = ?, localizacao = ?, gravidade = ?, gerado_por = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, alerta.getTipoAlerta());
            stmt.setString(2, alerta.getDescricao());
            stmt.setTimestamp(3, Timestamp.valueOf(alerta.getDataHora()));
            stmt.setString(4, alerta.getLocalizacao());
            stmt.setString(5, alerta.getGravidade());
            stmt.setInt(6, alerta.getGeradoPor());
            stmt.setInt(7, alerta.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar alerta", e);
        }
    }

    @Override
    public List<Alerta> getAll() {
        List<Alerta> alertas = new ArrayList<>();
        String sql = "SELECT * FROM T_FG_ALERTAS";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                alertas.add(map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar alertas", e);
        }
        return alertas;
    }

    @Override
    public List<Alerta> getAllAtivos() {
        return getAll(); // Não há campo de exclusão lógica
    }

    @Override
    public List<Alerta> get() {
        return getAll();
    }

    @Override
    public Optional<Alerta> getById(int id) {
        String sql = "SELECT * FROM T_FG_ALERTAS WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar alerta por ID", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Alerta> getByIdIncludingDeleted(int id) {
        return getById(id);
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM T_FG_ALERTAS WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência de alerta", e);
        }
    }

    private Alerta map(ResultSet rs) throws SQLException {
        return new Alerta(
                rs.getInt("id"),
                rs.getString("tipo_alerta"),
                rs.getString("descricao"),
                rs.getTimestamp("data_hora").toLocalDateTime(),
                rs.getString("localizacao"),
                rs.getString("gravidade"),
                rs.getInt("gerado_por")
        );
    }
}
