<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Produtos Abaixo do Mínimo</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>⚠️ Produtos Abaixo da Quantidade Mínima</h1>
        </header>
        
        <nav>
            <ul>
                <li><a href="index.jsp">Início</a></li>
                <li><a href="relatorios.jsp">Relatórios</a></li>
            </ul>
        </nav>
        
        <c:if test="${not empty produtos}">
            <div class="alert alert-warning">
                <strong>Atenção!</strong> Os produtos abaixo estão com quantidade abaixo do mínimo estabelecido e precisam de reposição.
            </div>
            
            <table>
                <thead>
                    <tr>
                        <th>Nome do Produto</th>
                        <th>Quantidade Mínima</th>
                        <th>Quantidade em Estoque</th>
                        <th>Diferença</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="produto" items="${produtos}">
                        <tr>
                            <td>${produto.nome}</td>
                            <td>${produto.quantidadeMinima}</td>
                            <td style="color: #dc3545; font-weight: bold;">${produto.quantidadeEstoque}</td>
                            <td style="color: #dc3545; font-weight: bold;">
                                ${produto.quantidadeMinima - produto.quantidadeEstoque} unidades abaixo
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        
        <c:if test="${empty produtos}">
            <div class="alert alert-success">
                <strong>Ótimo!</strong> Todos os produtos estão com quantidade acima ou igual à quantidade mínima.
            </div>
        </c:if>
        
        <div class="text-right mt-20">
            <button onclick="window.print()" class="btn">Imprimir</button>
            <a href="relatorios.jsp" class="btn btn-secondary">Voltar</a>
        </div>
    </div>
</body>
</html>

