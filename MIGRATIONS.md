# Migrations do Banco de Dados

Este projeto utiliza um sistema de migrations para gerenciar o schema do banco de dados.

## Estrutura

As migrations estão localizadas em: `src/main/resources/migrations/`

- `001_initial_schema.sql` - Schema inicial do banco de dados

## Como Funciona

As migrations são executadas **automaticamente** quando a aplicação inicia, através da classe `MigrationRunner` que é chamada no bloco `static` do `EstoqueDAO`.

**Importante**: Como o banco de dados H2 está configurado em memória (`jdbc:h2:mem:estoque`), as migrations são executadas toda vez que a aplicação inicia. Não é necessário executá-las manualmente em produção.

## Executar Migrations Manualmente

### Opção 1: Via Docker (Para Testes/Desenvolvimento)

Para executar migrations manualmente no container Docker (útil para testes):

```bash
./run-migrations-docker.sh
```

**Nota**: Como o banco é em memória, executar manualmente cria um novo banco temporário. As migrations reais são executadas automaticamente quando a aplicação inicia.

### Opção 2: Reiniciar o Container

Para forçar uma nova execução das migrations (reinicia o banco em memória):

```bash
docker compose restart estoque-app
```

### Opção 2: Compilar e Executar Localmente

Se você tiver Maven instalado localmente:

```bash
# Compilar
mvn clean compile

# Executar migrations
mvn exec:java -Dexec.mainClass="com.estoque.MigrationMain" -Dexec.classpathScope=compile
```

### Opção 3: Via Script

```bash
./run-migrations.sh
```

## Criar Nova Migration

1. Crie um novo arquivo SQL em `src/main/resources/migrations/` com o padrão:
   - `002_nome_da_migration.sql`
   - `003_outra_migration.sql`
   - etc.

2. Adicione o nome do arquivo na lista em `MigrationRunner.getAvailableMigrations()`

3. As migrations são executadas em ordem numérica

## Estrutura de uma Migration

```sql
-- Migration: 002_exemplo.sql
-- Descrição: Descrição da migration
-- Data: YYYY-MM-DD

-- Seus comandos SQL aqui
CREATE TABLE IF NOT EXISTS exemplo (
    id INT IDENTITY PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);
```

## Controle de Versão

O sistema mantém um registro das migrations aplicadas na tabela `schema_migrations`:

- `version` - Nome do arquivo de migration
- `applied_at` - Data/hora de aplicação

Migrations já aplicadas não serão executadas novamente.

