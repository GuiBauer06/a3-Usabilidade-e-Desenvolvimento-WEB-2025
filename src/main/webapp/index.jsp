<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sistema de Controle de Estoque</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>📦 Sistema de Controle de Estoque</h1>
            <p>Gerencie seu estoque de forma eficiente</p>
        </header>
        
        <nav>
            <ul>
                <li><a href="produto?action=listar">Produtos</a></li>
                <li><a href="categoria?action=listar">Categorias</a></li>
                <li><a href="movimentacao">Movimentações</a></li>
                <li><a href="reajuste-preco">Reajuste de Preços</a></li>
                <li><a href="relatorios.jsp">Relatórios</a></li>
            </ul>
        </nav>
        
        <main>
            <h2>Bem-vindo ao Sistema de Controle de Estoque</h2>
            <p>Este sistema permite gerenciar produtos, categorias, movimentações de estoque e gerar relatórios diversos.</p>
            
            <div class="stats">
                <div class="stat-card">
                    <h3>Produtos</h3>
                    <p>Cadastro e gestão</p>
                </div>
                <div class="stat-card">
                    <h3>Categorias</h3>
                    <p>Organização por tipo</p>
                </div>
                <div class="stat-card">
                    <h3>Movimentações</h3>
                    <p>Entrada e saída</p>
                </div>
                <div class="stat-card">
                    <h3>Relatórios</h3>
                    <p>Análises e balanços</p>
                </div>
            </div>
        </main>
    </div>
</body>
</html>

