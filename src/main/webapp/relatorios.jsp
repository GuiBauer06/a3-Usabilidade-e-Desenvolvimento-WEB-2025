<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Relatórios</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>📈 Relatórios</h1>
        </header>
        
        <nav>
            <ul>
                <li><a href="index.jsp">Início</a></li>
                <li><a href="produto?action=listar">Produtos</a></li>
                <li><a href="categoria?action=listar">Categorias</a></li>
                <li><a href="movimentacao">Movimentações</a></li>
                <li><a href="reajuste-preco">Reajuste de Preços</a></li>
                <li><a href="relatorios.jsp">Relatórios</a></li>
            </ul>
        </nav>
        
        <h2>Selecione um Relatório</h2>
        
        <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); gap: 20px; margin-top: 30px;">
            <a href="relatorio?tipo=lista-precos" class="btn" style="padding: 30px; text-align: center; display: block;">
                <h3>📋 Lista de Preços</h3>
                <p>Relação de todos os produtos com preços em ordem alfabética</p>
            </a>
            
            <a href="relatorio?tipo=balanco" class="btn" style="padding: 30px; text-align: center; display: block;">
                <h3>💵 Balanço Físico/Financeiro</h3>
                <p>Quantidade disponível e valor total do estoque</p>
            </a>
            
            <a href="relatorio?tipo=abaixo-minimo" class="btn" style="padding: 30px; text-align: center; display: block;">
                <h3>⚠️ Produtos Abaixo do Mínimo</h3>
                <p>Produtos que precisam de reposição</p>
            </a>
            
            <a href="relatorio?tipo=por-categoria" class="btn" style="padding: 30px; text-align: center; display: block;">
                <h3>📊 Produtos por Categoria</h3>
                <p>Quantidade de produtos distintos por categoria</p>
            </a>
            
            <a href="relatorio?tipo=mais-movimentado" class="btn" style="padding: 30px; text-align: center; display: block;">
                <h3>🔄 Produtos Mais Movimentados</h3>
                <p>Produtos com mais entrada e mais saída</p>
            </a>
        </div>
    </div>
</body>
</html>

