package com.gerenciador.model.dao.impl;

import com.gerenciador.db.DB;
import com.gerenciador.db.DbExecption;
import com.gerenciador.model.dao.UsuarioDao;
import com.gerenciador.model.entities.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da interface UsuarioDao usando JDBC.
 */
public class UsuarioDaoImpl implements UsuarioDao {

    private Connection connection;

    public UsuarioDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Usuario usuario) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO Usuario (nome, email) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    usuario.setId(id);
                }
                DB.closeResultSet(resultSet);
            } else {
                throw new DbExecption("Erro inesperado! Nenhuma linha foi afetada.");
            }
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        } finally {
            DB.closeStatement(statement);
        }
    }

    @Override
    public void update(Usuario usuario) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "UPDATE Usuario SET nome = ?, email = ? WHERE id = ?"
            );
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setInt(3, usuario.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        } finally {
            DB.closeStatement(statement);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM Usuario WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        } finally {
            DB.closeStatement(statement);
        }
    }

    @Override
    public Usuario findById(Integer id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM Usuario WHERE id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Usuario usuario = instantiateUsuario(resultSet);
                return usuario;
            }
            return null;
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public List<Usuario> findAll() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM Usuario ORDER BY nome");
            resultSet = statement.executeQuery();

            List<Usuario> usuarios = new ArrayList<>();
            while (resultSet.next()) {
                Usuario usuario = instantiateUsuario(resultSet);
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
    }

    /**
     * Método auxiliar para instanciar um objeto Usuario a partir do ResultSet.
     */
    private Usuario instantiateUsuario(ResultSet resultSet) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(resultSet.getInt("id"));
        usuario.setNome(resultSet.getString("nome"));
        usuario.setEmail(resultSet.getString("email"));
        return usuario;
    }
}
