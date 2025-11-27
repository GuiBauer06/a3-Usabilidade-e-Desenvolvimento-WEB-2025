<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reajuste de Preços</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>💰 Reajuste de Preços</h1>
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
        
        <c:if test="${not empty mensagem}">
            <div class="alert alert-success">
                ${mensagem}
            </div>
        </c:if>
        
        <div class="alert alert-info">
            <strong>Informação:</strong> O reajuste será aplicado a todos os produtos do estoque. 
            Use valores positivos para aumento e negativos para redução.
        </div>
        
        <form action="reajuste-preco" method="post" onsubmit="return confirm('Tem certeza que deseja aplicar este reajuste a todos os produtos?')">
            <div class="form-group">
                <label for="percentual">Percentual de Reajuste (%) *</label>
                <input type="number" id="percentual" name="percentual" 
                       step="0.01" required placeholder="Ex: 10 para aumento de 10%, -5 para redução de 5%">
            </div>
            
            <div class="text-right">
                <button type="submit" class="btn btn-success">Aplicar Reajuste</button>
            </div>
        </form>
    </div>
</body>
</html>

