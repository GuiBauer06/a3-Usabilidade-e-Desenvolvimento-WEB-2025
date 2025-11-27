<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Categorias</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>🏷️ Gestão de Categorias</h1>
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
        
        <div class="text-right mb-20">
            <a href="categoria-form.jsp" class="btn btn-success">+ Nova Categoria</a>
        </div>
        
        <c:if test="${not empty categorias}">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Tamanho</th>
                        <th>Embalagem</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="categoria" items="${categorias}">
                        <tr>
                            <td>${categoria.id}</td>
                            <td>${categoria.nome}</td>
                            <td>${categoria.tamanho}</td>
                            <td>${categoria.embalagem}</td>
                            <td class="actions">
                                <a href="categoria?action=editar&id=${categoria.id}" class="btn">Editar</a>
                                <a href="categoria?action=excluir&id=${categoria.id}" 
                                   class="btn btn-danger" 
                                   onclick="return confirm('Tem certeza que deseja excluir esta categoria?')">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        
        <c:if test="${empty categorias}">
            <div class="empty-message">
                <p>Nenhuma categoria cadastrada ainda.</p>
                <a href="categoria-form.jsp" class="btn btn-success mt-20">Cadastrar Primeira Categoria</a>
            </div>
        </c:if>
    </div>
</body>
</html>

