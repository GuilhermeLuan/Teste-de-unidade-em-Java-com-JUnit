package com.gerenciador.application;

import com.gerenciador.model.dao.DaoFactory;
import com.gerenciador.model.dao.UsuarioDao;
import com.gerenciador.model.entities.Usuario;

import java.util.List;

/**
 * Classe principal para demonstrar o uso do sistema de cadastro de usuários.
 */
public class Main {

    public static void main(String[] args) {
        // Criar instância do DAO usando a Factory
        UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();

        System.out.println("=== Sistema de Cadastro de Usuários ===\n");

        // 1. Inserir usuários
        System.out.println("1. Inserindo usuários...");
        Usuario usuario1 = new Usuario("João Silva", "joao1.silva@email.com");
        Usuario usuario2 = new Usuario("Maria Santos", "maria1.santos@email.com");
        Usuario usuario3 = new Usuario("Pedro Oliveira", "pedro1.oliveira@email.com");

        usuarioDao.insert(usuario1);
        usuarioDao.insert(usuario2);
        usuarioDao.insert(usuario3);
        System.out.println("✓ Usuários inseridos com sucesso!\n");

        // 2. Listar todos os usuários
        System.out.println("2. Listando todos os usuários:");
        List<Usuario> usuarios = usuarioDao.findAll();
        for (Usuario u : usuarios) {
            System.out.println("   " + u);
        }
        System.out.println();

        // 3. Buscar usuário por ID
        System.out.println("3. Buscando usuário por ID (ID = 1):");
        Usuario usuarioEncontrado = usuarioDao.findById(1);
        if (usuarioEncontrado != null) {
            System.out.println("   " + usuarioEncontrado);
        }
        System.out.println();

        // 4. Atualizar usuário
        System.out.println("4. Atualizando usuário (ID = 1)...");
        if (usuarioEncontrado != null) {
            usuarioEncontrado.setNome("João Pedro Silva");
            usuarioEncontrado.setEmail("joao.pedro@email.com");
            usuarioDao.update(usuarioEncontrado);
            System.out.println("✓ Usuário atualizado: " + usuarioDao.findById(1));
        }
        System.out.println();

        // 5. Excluir usuário
        System.out.println("5. Excluindo usuário (ID = 3)...");
        usuarioDao.deleteById(3);
        System.out.println("✓ Usuário excluído com sucesso!\n");

        // 6. Listar todos novamente
        System.out.println("6. Listando usuários após exclusão:");
        usuarios = usuarioDao.findAll();
        for (Usuario u : usuarios) {
            System.out.println("   " + u);
        }

        System.out.println("\n=== Fim da demonstração ===");
    }
}
