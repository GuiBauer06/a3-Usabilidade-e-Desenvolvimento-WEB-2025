<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Preços</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>📋 Lista de Preços</h1>
        </header>
        
        <nav>
            <ul>
                <li><a href="index.jsp">Início</a></li>
                <li><a href="relatorios.jsp">Relatórios</a></li>
            </ul>
        </nav>
        
        <c:if test="${not empty produtos}">
            <table>
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Preço Unitário</th>
                        <th>Unidade</th>
                        <th>Categoria</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="produto" items="${produtos}">
                        <tr>
                            <td>${produto.nome}</td>
                            <td>R$ ${produto.precoUnitario}</td>
                            <td>${produto.unidade}</td>
                            <td>${produto.categoriaNome != null ? produto.categoriaNome : 'Sem categoria'}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        
        <c:if test="${empty produtos}">
            <div class="empty-message">
                <p>Nenhum produto cadastrado.</p>
            </div>
        </c:if>
        
        <div class="text-right mt-20">
            <button onclick="window.print()" class="btn">Imprimir</button>
            <a href="relatorios.jsp" class="btn btn-secondary">Voltar</a>
        </div>
    </div>
</body>
</html>

