<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Produtos</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>📦 Gestão de Produtos</h1>
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
            <a href="produto-form.jsp" class="btn btn-success">+ Novo Produto</a>
        </div>
        
        <c:if test="${not empty produtos}">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Preço Unitário</th>
                        <th>Unidade</th>
                        <th>Quantidade</th>
                        <th>Qtd. Mínima</th>
                        <th>Qtd. Máxima</th>
                        <th>Categoria</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="produto" items="${produtos}">
                        <tr>
                            <td>${produto.id}</td>
                            <td>${produto.nome}</td>
                            <td>R$ ${produto.precoUnitario}</td>
                            <td>${produto.unidade}</td>
                            <td>${produto.quantidadeEstoque}</td>
                            <td>${produto.quantidadeMinima}</td>
                            <td>${produto.quantidadeMaxima}</td>
                            <td>${produto.categoriaNome != null ? produto.categoriaNome : 'Sem categoria'}</td>
                            <td class="actions">
                                <a href="produto?action=editar&id=${produto.id}" class="btn">Editar</a>
                                <a href="produto?action=excluir&id=${produto.id}" 
                                   class="btn btn-danger" 
                                   onclick="return confirm('Tem certeza que deseja excluir este produto?')">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        
        <c:if test="${empty produtos}">
            <div class="empty-message">
                <p>Nenhum produto cadastrado ainda.</p>
                <a href="produto-form.jsp" class="btn btn-success mt-20">Cadastrar Primeiro Produto</a>
            </div>
        </c:if>
    </div>
</body>
</html>

