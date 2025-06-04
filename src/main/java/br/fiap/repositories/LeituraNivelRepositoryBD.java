package br.fiap.repositories;

import br.fiap.entities.LeituraNivel;
import br.fiap.infrastructure.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LeituraNivelRepositoryBD implements _CrudRepository<LeituraNivel> {

    private Connection connection;

    public LeituraNivelRepositoryBD() {
        try {
            this.connection = DatabaseConfig.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados", e);
        }
    }

    @Override
    public void add(LeituraNivel leitura) {
        String sql = "INSERT INTO T_FG_LEITURAS_NIVEL (id, data_hora, localizacao, nivel, fonte) " +
                "VALUES (seq_leituras_nivel.NEXTVAL, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(leitura.getDataHora()));
            stmt.setString(2, leitura.getLocalizacao());
            stmt.setDouble(3, leitura.getNivel());
            stmt.setString(4, leitura.getFonte());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir leitura de nível", e);
        }
    }

    @Override
    public boolean remove(LeituraNivel leitura) {
        return removeById(leitura.getId());
    }

    @Override
    public boolean removeById(int id) {
        String sql = "DELETE FROM T_FG_LEITURAS_NIVEL WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover leitura por ID", e);
        }
    }

    @Override
    public boolean delete(LeituraNivel leitura) {
        return false; // Exclusão lógica não implementada
    }

    @Override
    public boolean deleteById(int id) {
        return false; // Exclusão lógica não implementada
    }

    @Override
    public boolean update(LeituraNivel leitura) {
        String sql = "UPDATE T_FG_LEITURAS_NIVEL SET data_hora = ?, localizacao = ?, nivel = ?, fonte = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(leitura.getDataHora()));
            stmt.setString(2, leitura.getLocalizacao());
            stmt.setDouble(3, leitura.getNivel());
            stmt.setString(4, leitura.getFonte());
            stmt.setInt(5, leitura.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar leitura", e);
        }
    }

    @Override
    public List<LeituraNivel> getAll() {
        List<LeituraNivel> leituras = new ArrayList<>();
        String sql = "SELECT * FROM T_FG_LEITURAS_NIVEL";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                leituras.add(map(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar leituras", e);
        }
        return leituras;
    }

    @Override
    public List<LeituraNivel> getAllAtivos() {
        return getAll(); // Não há lógica de exclusão implementada
    }

    @Override
    public List<LeituraNivel> get() {
        return getAll(); // Retorna todos como padrão
    }

    @Override
    public Optional<LeituraNivel> getById(int id) {
        String sql = "SELECT * FROM T_FG_LEITURAS_NIVEL WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar leitura por ID", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<LeituraNivel> getByIdIncludingDeleted(int id) {
        return getById(id); // Sem campo de exclusão lógica
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM T_FG_LEITURAS_NIVEL WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência", e);
        }
        return false;
    }

    private LeituraNivel map(ResultSet rs) throws SQLException {
        return new LeituraNivel(
                rs.getInt("id"),
                rs.getTimestamp("data_hora").toLocalDateTime(),
                rs.getString("localizacao"),
                rs.getDouble("nivel"),
                rs.getString("fonte")
        );
    }
}
