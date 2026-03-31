package com.gerenciador.model.dao;

import com.gerenciador.model.entities.Usuario;

import java.util.List;

/**
 * Interface DAO para operações CRUD da entidade Usuario.
 */
public interface UsuarioDao {

    /**
     * Insere um novo usuário no banco de dados.
     * @param usuario O usuário a ser inserido.
     */
    void insert(Usuario usuario);

    /**
     * Atualiza os dados de um usuário existente.
     * @param usuario O usuário com os dados atualizados.
     */
    void update(Usuario usuario);

    /**
     * Remove um usuário do banco de dados pelo ID.
     * @param id O ID do usuário a ser removido.
     */
    void deleteById(Integer id);

    /**
     * Busca um usuário pelo ID.
     * @param id O ID do usuário.
     * @return O usuário encontrado ou null se não existir.
     */
    Usuario findById(Integer id);

    /**
     * Busca todos os usuários cadastrados.
     * @return Lista de todos os usuários.
     */
    List<Usuario> findAll();
}
