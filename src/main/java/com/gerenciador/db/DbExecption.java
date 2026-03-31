package com.gerenciador.db;

/**
 * Classe utilitária para o lançamento de exceções customizáveis.
 */
public class DbExecption extends RuntimeException {
    public DbExecption(String message) {
        super(message);
    }
}
