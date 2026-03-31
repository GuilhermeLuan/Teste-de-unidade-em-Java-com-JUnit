package com.gerenciador.db;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class TestConexaoDB {

    @Test
    @DisplayName("Deve estabelecer conexão com o banco de dados")
    void testConexaoComBancoDeDados() {
        Connection connection = null;
        try {
            connection = DB.getConnection();
            
            assertNotNull(connection, "A conexão com o banco de dados não deve ser nula");
            assertFalse(connection.isClosed(), "A conexão deve estar aberta");
            
        } catch (Exception e) {
            fail("Falha ao conectar com o banco de dados: " + e.getMessage());
        } finally {
            DB.closeConnection();
        }
    }

    @Test
    @DisplayName("Deve falhar ao conectar com credenciais inválidas")
    void testFalhaConexaoCredenciaisInvalidas() {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "senha_errada");
        
        String url = "jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false";
        
        assertThrows(SQLException.class, () -> {
            DriverManager.getConnection(url, properties);
        }, "Deve lançar SQLException ao usar credenciais inválidas");
    }
}
