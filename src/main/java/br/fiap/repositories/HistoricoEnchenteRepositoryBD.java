
package br.fiap.repositories;

import br.fiap.entities.HistoricoEnchente;
import br.fiap.infrastructure.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HistoricoEnchenteRepositoryBD implements _CrudRepository<HistoricoEnchente> {

    private Connection connection;

    public HistoricoEnchenteRepositoryBD() {
        try {
            this.connection = DatabaseConfig.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados", e);
        }
    }

    @Override
    public void add(HistoricoEnchente enchente) {
        String sql = "INSERT INTO T_FG_HISTORICO_ENCHENTES (id, data_hora, localizacao, nivel_maximo, descricao) " +
                "VALUES (seq_historico_enchentes.NEXTVAL, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(enchente.getDataHora()));
            stmt.setString(2, enchente.getLocalizacao());
            stmt.setDouble(3, enchente.getNivelMaximo());
            stmt.setString(4, enchente.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir histórico de enchente", e);
        }
    }

    @Override
    public boolean remove(HistoricoEnchente enchente) {
        return removeById(enchente.getId());
    }

    @Override
    public boolean removeById(int id) {
        String sql = "DELETE FROM T_FG_HISTORICO_ENCHENTES WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover histórico de enchente", e);
        }
    }

    @Override
    public boolean delete(HistoricoEnchente enchente) {
        return false; // Exclusão lógica não implementada
    }

    @Override
    public boolean deleteById(int id) {
        return false; // Exclusão lógica não implementada
    }

    @Override
    public boolean update(HistoricoEnchente enchente) {
        String sql = "UPDATE T_FG_HISTORICO_ENCHENTES SET data_hora = ?, localizacao = ?, nivel_maximo = ?, descricao = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(enchente.getDataHora()));
            stmt.setString(2, enchente.getLocalizacao());
            stmt.setDouble(3, enchente.getNivelMaximo());
            stmt.setString(4, enchente.getDescricao());
            stmt.setInt(5, enchente.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar histórico de enchente", e);
        }
    }

    @Override
    public List<HistoricoEnchente> getAll() {
        List<HistoricoEnchente> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_FG_HISTORICO_ENCHENTES";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar históricos de enchentes", e);
        }
        return lista;
    }

    @Override
    public List<HistoricoEnchente> getAllAtivos() {
        return getAll(); // Exclusão lógica não implementada
    }

    @Override
    public List<HistoricoEnchente> get() {
        return getAll();
    }

    @Override
    public Optional<HistoricoEnchente> getById(int id) {
        String sql = "SELECT * FROM T_FG_HISTORICO_ENCHENTES WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar histórico por ID", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<HistoricoEnchente> getByIdIncludingDeleted(int id) {
        return getById(id); // Não há lógica de exclusão
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM T_FG_HISTORICO_ENCHENTES WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência de histórico", e);
        }
    }

    private HistoricoEnchente map(ResultSet rs) throws SQLException {
        return new HistoricoEnchente(
                rs.getInt("id"),
                rs.getTimestamp("data_hora").toLocalDateTime(),
                rs.getString("localizacao"),
                rs.getDouble("nivel_maximo"),
                rs.getString("descricao")
        );
    }
}
