<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Produtos por Categoria</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>📊 Quantidade de Produtos por Categoria</h1>
        </header>
        
        <nav>
            <ul>
                <li><a href="index.jsp">Início</a></li>
                <li><a href="relatorios.jsp">Relatórios</a></li>
            </ul>
        </nav>
        
        <c:if test="${not empty dados}">
            <table>
                <thead>
                    <tr>
                        <th>Categoria</th>
                        <th>Quantidade de Produtos</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${dados}">
                        <tr>
                            <td>${item[0]}</td>
                            <td class="text-center">${item[1]}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        
        <c:if test="${empty dados}">
            <div class="empty-message">
                <p>Nenhuma categoria cadastrada.</p>
            </div>
        </c:if>
        
        <div class="text-right mt-20">
            <button onclick="window.print()" class="btn">Imprimir</button>
            <a href="relatorios.jsp" class="btn btn-secondary">Voltar</a>
        </div>
    </div>
</body>
</html>

