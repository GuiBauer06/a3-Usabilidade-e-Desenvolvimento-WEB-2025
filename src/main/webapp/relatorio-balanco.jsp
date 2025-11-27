<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Balanço Físico/Financeiro</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>💵 Balanço Físico/Financeiro</h1>
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
                        <th>Quantidade Disponível</th>
                        <th>Valor Unitário</th>
                        <th>Valor Total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="produto" items="${produtos}">
                        <tr>
                            <td>${produto.nome}</td>
                            <td>${produto.quantidadeEstoque} ${produto.unidade}</td>
                            <td>R$ ${produto.precoUnitario}</td>
                            <td>R$ <fmt:formatNumber value="${produto.valorTotal}" minFractionDigits="2" maxFractionDigits="2"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr style="background: #667eea; color: white; font-weight: bold;">
                        <td colspan="3" class="text-right">VALOR TOTAL DO ESTOQUE:</td>
                        <td>R$ <fmt:formatNumber value="${valorTotal}" minFractionDigits="2" maxFractionDigits="2"/></td>
                    </tr>
                </tfoot>
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

