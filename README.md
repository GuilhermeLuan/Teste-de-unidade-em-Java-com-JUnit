# Sistema de Cadastro de Usuários

Sistema simples de cadastro de usuários desenvolvido em Java para fins acadêmicos, demonstrando o uso do padrão DAO (Data Access Object).

**Repositório de Testes:** https://github.com/GuilhermeLuan/Teste-de-unidade-em-Java-com-JUnit

---

## ⚙️ Como Rodar o Projeto

### Pré-requisitos
- Java 21 ou superior
- Docker e Docker Compose
- Maven

### Passos

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/GuilhermeLuan/Teste-de-unidade-em-Java-com-JUnit
   cd gerenciador-de-estoque
   ```

2. **Inicie o banco de dados MySQL:**
   ```bash
   docker-compose up -d
   ```
   
   O banco será criado automaticamente com a tabela `Usuario`.

3. **Execute os testes:**
   ```bash
   ./mvnw test
   ```
   
   O projeto inclui 4 testes JUnit:
   - ✅ Teste de conexão bem-sucedida
   - ✅ Teste de falha com credenciais inválidas

4. **Execute a aplicação de exemplo:**
   ```bash
   ./mvnw compile exec:java -Dexec.mainClass="com.gerenciador.application.Main"
   ```

5. **Para buildar o projeto:**
   ```bash
   ./mvnw clean install
   ```

---

## Estrutura do Banco de Dados

```sql
CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);
```

---
