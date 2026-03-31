package com.gerenciador.model.dao;

import com.gerenciador.db.DB;
import com.gerenciador.model.dao.impl.UsuarioDaoImpl;

/**
 * Esta classe fornece métodos para instanciar implementações
 * das interfaces DAO utilizadas no sistema.
 */
public class DaoFactory {

    /**
     * Cria e retorna uma instância de UsuarioDao.
     */
    public static UsuarioDao createUsuarioDao() {
        return new UsuarioDaoImpl(DB.getConnection());
    }
}