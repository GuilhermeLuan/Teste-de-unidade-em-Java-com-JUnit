package com.gerenciador.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Classe utilitária para gerenciar a conexão com o banco de dados.
 * Contém métodos para abrir e fechar conexões, além de carregar
 * configurações de propriedades do banco de dados.
 */

public class DB {

    private static Connection connection = null;

    /**
     * Faz a conexão com o banco de dados.
     * Se ainda não houver uma conexão, cria uma nova.
     */

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Properties properties = loadProperties();
                String url = properties.getProperty("dburl");
                connection = DriverManager.getConnection(url, properties);
            } catch (SQLException e) {
                throw new DbExecption(e.getMessage());
            }
        }
        return connection;
    }

    /**
     * Fecha a conexão ativa com o banco de dados, se existir.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DbExecption(e.getMessage());
            }
        }
    }

    /**
     * Fecha um objeto Statement, se ele não for nulo.
     */
    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DbExecption(e.getMessage());
            }
        }
    }

    /**
     * Fecha um objeto ResultSet, se ele não for nulo.
     */
    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DbExecption(e.getMessage());
            }
        }
    }

    /**
     * Carrega as propriedades do arquivo de configuração.
     * O arquivo deve estar localizado no caminho "src/main/resources/db.properties".
     */
    public static Properties loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/db.properties")) {
            properties.load(fileInputStream);
            return properties;
        } catch (IOException e) {
            throw new DbExecption(e.getMessage());
        }
    }
}
