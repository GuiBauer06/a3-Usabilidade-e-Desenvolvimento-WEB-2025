<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${categoria != null ? 'Editar' : 'Nova'} Categoria</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>${categoria != null ? '✏️ Editar' : '➕ Nova'} Categoria</h1>
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
        
        <form action="categoria" method="post">
            <input type="hidden" name="action" value="salvar">
            <c:if test="${categoria != null}">
                <input type="hidden" name="id" value="${categoria.id}">
            </c:if>
            
            <div class="form-group">
                <label for="nome">Nome da Categoria *</label>
                <input type="text" id="nome" name="nome" value="${categoria.nome}" required>
            </div>
            
            <div class="form-group">
                <label for="tamanho">Tamanho *</label>
                <select id="tamanho" name="tamanho" required>
                    <option value="">Selecione o tamanho</option>
                    <option value="Pequeno" ${categoria != null && categoria.tamanho == 'Pequeno' ? 'selected' : ''}>Pequeno</option>
                    <option value="Médio" ${categoria != null && categoria.tamanho == 'Médio' ? 'selected' : ''}>Médio</option>
                    <option value="Grande" ${categoria != null && categoria.tamanho == 'Grande' ? 'selected' : ''}>Grande</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="embalagem">Embalagem *</label>
                <select id="embalagem" name="embalagem" required>
                    <option value="">Selecione a embalagem</option>
                    <option value="Lata" ${categoria != null && categoria.embalagem == 'Lata' ? 'selected' : ''}>Lata</option>
                    <option value="Vidro" ${categoria != null && categoria.embalagem == 'Vidro' ? 'selected' : ''}>Vidro</option>
                    <option value="Plástico" ${categoria != null && categoria.embalagem == 'Plástico' ? 'selected' : ''}>Plástico</option>
                </select>
            </div>
            
            <div class="text-right">
                <a href="categoria?action=listar" class="btn btn-secondary">Cancelar</a>
                <button type="submit" class="btn btn-success">Salvar</button>
            </div>
        </form>
    </div>
</body>
</html>

