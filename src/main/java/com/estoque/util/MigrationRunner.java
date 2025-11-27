package com.estoque.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MigrationRunner {
    private static final String URL = "jdbc:h2:mem:estoque;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    
    private static boolean migrationsExecuted = false;
    private static final Object lock = new Object();
    
    public static void runMigrations() {
        // Garantir que as migrations sejam executadas apenas uma vez
        if (migrationsExecuted) {
            return;
        }
        
        synchronized (lock) {
            if (migrationsExecuted) {
                return;
            }
            
            try {
                Class.forName("org.h2.Driver");
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                
                // Criar tabela de controle de migrations se não existir
                createMigrationTable(conn);
                
                // Listar todas as migrations disponíveis
                List<String> migrations = getAvailableMigrations();
                
                // Executar migrations pendentes
                for (String migration : migrations) {
                    if (!isMigrationApplied(conn, migration)) {
                        System.out.println("Executando migration: " + migration);
                        executeMigration(conn, migration);
                        recordMigration(conn, migration);
                        System.out.println("Migration " + migration + " aplicada com sucesso!");
                    } else {
                        System.out.println("Migration " + migration + " já foi aplicada, pulando...");
                    }
                }
                
                // Verificar se as tabelas foram criadas
                verifyTables(conn);
                
                conn.close();
                migrationsExecuted = true;
                System.out.println("Todas as migrations foram executadas com sucesso!");
                
            } catch (Exception e) {
                System.err.println("Erro ao executar migrations: " + e.getMessage());
                e.printStackTrace();
                // Não lançar exceção para não impedir a inicialização da aplicação
                // As migrations serão tentadas novamente na próxima conexão
                System.err.println("AVISO: Migrations não foram executadas. A aplicação pode não funcionar corretamente.");
            }
        }
    }
    
    private static void verifyTables(Connection conn) throws Exception {
        String[] tables = {"categorias", "produtos", "movimentacoes", "schema_migrations"};
        try (Statement stmt = conn.createStatement()) {
            for (String table : tables) {
                String sql = "SELECT COUNT(*) FROM " + table;
                try {
                    stmt.executeQuery(sql);
                    System.out.println("Tabela " + table + " verificada com sucesso");
                } catch (Exception e) {
                    System.err.println("ERRO: Tabela " + table + " não foi criada corretamente: " + e.getMessage());
                    // Listar todas as tabelas para debug
                    try (ResultSet rs = stmt.executeQuery("SHOW TABLES")) {
                        System.err.println("Tabelas existentes:");
                        while (rs.next()) {
                            System.err.println("  - " + rs.getString(1));
                        }
                    }
                    throw new Exception("Tabela " + table + " não foi criada corretamente", e);
                }
            }
        }
    }
    
    private static void createMigrationTable(Connection conn) throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS schema_migrations (" +
                    "version VARCHAR(50) PRIMARY KEY, " +
                    "applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }
    
    private static List<String> getAvailableMigrations() {
        List<String> migrations = new ArrayList<>();
        
        // Lista de migrations na ordem de execução
        migrations.add("001_initial_schema.sql");
        // Adicione mais migrations aqui conforme necessário
        
        return migrations;
    }
    
    private static boolean isMigrationApplied(Connection conn, String migration) throws Exception {
        String sql = "SELECT COUNT(*) FROM schema_migrations WHERE version = ?";
        try (java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, migration);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    private static void executeMigration(Connection conn, String migration) throws Exception {
        String sql = loadMigrationFile(migration);
        
        // Dividir em comandos individuais (separados por ;)
        // Usar regex para dividir corretamente, preservando strings
        String[] commands = sql.split(";(?=(?:[^']*'[^']*')*[^']*$)");
        
        try (Statement stmt = conn.createStatement()) {
            for (String command : commands) {
                command = command.trim();
                
                // Remover comentários de linha completa
                String[] lines = command.split("\n");
                StringBuilder cleanCommand = new StringBuilder();
                
                for (String line : lines) {
                    line = line.trim();
                    // Ignorar linhas vazias e comentários completos
                    if (line.isEmpty() || line.startsWith("--")) {
                        continue;
                    }
                    // Remover comentários inline
                    if (line.contains("--")) {
                        line = line.substring(0, line.indexOf("--")).trim();
                    }
                    if (!line.isEmpty()) {
                        cleanCommand.append(line).append(" ");
                    }
                }
                
                command = cleanCommand.toString().trim();
                
                if (!command.isEmpty()) {
                    try {
                        stmt.execute(command);
                        System.out.println("Comando executado: " + command.substring(0, Math.min(60, command.length())) + "...");
                    } catch (Exception e) {
                        System.err.println("Erro ao executar comando: " + command);
                        System.err.println("Erro: " + e.getMessage());
                        throw e;
                    }
                }
            }
        }
    }
    
    private static String loadMigrationFile(String filename) throws Exception {
        InputStream is = MigrationRunner.class.getClassLoader()
                .getResourceAsStream("migrations/" + filename);
        
        if (is == null) {
            throw new Exception("Arquivo de migration não encontrado: " + filename);
        }
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }
    
    private static void recordMigration(Connection conn, String migration) throws Exception {
        String sql = "INSERT INTO schema_migrations (version) VALUES (?)";
        try (java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, migration);
            pstmt.executeUpdate();
        }
    }
}

