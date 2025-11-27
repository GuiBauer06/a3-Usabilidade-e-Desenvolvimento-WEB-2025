#!/bin/bash

# Script para executar migrations do banco de dados

echo "=== Executando Migrations do Banco de Dados ==="

# Compilar o projeto
echo "Compilando o projeto..."
mvn clean compile

# Executar migrations
echo "Executando migrations..."
mvn exec:java -Dexec.mainClass="com.estoque.MigrationMain" -Dexec.classpathScope=compile

echo "=== Migrations concluídas ==="

