<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Produtos Mais Movimentados</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>🔄 Produtos Mais Movimentados</h1>
        </header>
        
        <nav>
            <ul>
                <li><a href="index.jsp">Início</a></li>
                <li><a href="relatorios.jsp">Relatórios</a></li>
            </ul>
        </nav>
        
        <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 30px; margin: 30px 0;">
            <div>
                <h2 style="color: #dc3545;">Produto com Mais Saída</h2>
                <c:if test="${not empty maisSaida}">
                    <div class="stat-card" style="background: linear-gradient(135deg, #dc3545 0%, #c82333 100%);">
                        <h3>${maisSaida[0]}</h3>
                        <p>Total de saídas: ${maisSaida[1]} unidades</p>
                    </div>
                </c:if>
                <c:if test="${empty maisSaida}">
                    <div class="empty-message">
                        <p>Nenhuma saída registrada ainda.</p>
                    </div>
                </c:if>
            </div>
            
            <div>
                <h2 style="color: #28a745;">Produto com Mais Entrada</h2>
                <c:if test="${not empty maisEntrada}">
                    <div class="stat-card" style="background: linear-gradient(135deg, #28a745 0%, #218838 100%);">
                        <h3>${maisEntrada[0]}</h3>
                        <p>Total de entradas: ${maisEntrada[1]} unidades</p>
                    </div>
                </c:if>
                <c:if test="${empty maisEntrada}">
                    <div class="empty-message">
                        <p>Nenhuma entrada registrada ainda.</p>
                    </div>
                </c:if>
            </div>
        </div>
        
        <div class="text-right mt-20">
            <button onclick="window.print()" class="btn">Imprimir</button>
            <a href="relatorios.jsp" class="btn btn-secondary">Voltar</a>
        </div>
    </div>
</body>
</html>

