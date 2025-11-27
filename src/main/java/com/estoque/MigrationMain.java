package com.estoque;

import com.estoque.util.MigrationRunner;

/**
 * Classe principal para executar migrations manualmente
 * Uso: java -cp "target/classes:target/dependency/*" com.estoque.MigrationMain
 */
public class MigrationMain {
    public static void main(String[] args) {
        System.out.println("=== Executando Migrations do Banco de Dados ===");
        MigrationRunner.runMigrations();
        System.out.println("=== Migrations concluídas ===");
    }
}

