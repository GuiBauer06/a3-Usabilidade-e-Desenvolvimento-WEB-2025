#!/bin/bash

# Script para executar migrations dentro do container Docker

echo "=== Executando Migrations do Banco de Dados no Docker ==="

# Verificar se o container está rodando
if ! docker ps | grep -q sistema-estoque; then
    echo "Container não está rodando. Iniciando..."
    docker compose up -d
    sleep 5
fi

# Executar migrations dentro do container
echo "Executando migrations..."
docker exec sistema-estoque sh -c "cd /usr/local/tomcat/webapps/ROOT/WEB-INF && java -cp 'classes:lib/*' com.estoque.MigrationMain"

echo "=== Migrations concluídas ==="

