<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${produto != null ? 'Editar' : 'Novo'} Produto</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>${produto != null ? '✏️ Editar' : '➕ Novo'} Produto</h1>
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
        
        <form action="produto" method="post" onsubmit="return validarFormulario()">
            <input type="hidden" name="action" value="salvar">
            <c:if test="${produto != null}">
                <input type="hidden" name="id" value="${produto.id}">
            </c:if>
            
            <div class="form-group">
                <label for="nome">Nome do Produto *</label>
                <input type="text" id="nome" name="nome" value="${produto.nome}" required>
            </div>
            
            <div class="form-group">
                <label for="precoUnitario">Preço Unitário (R$) *</label>
                <input type="number" id="precoUnitario" name="precoUnitario" 
                       step="0.01" min="0" value="${produto.precoUnitario}" required>
            </div>
            
            <div class="form-group">
                <label for="unidade">Unidade de Medida *</label>
                <input type="text" id="unidade" name="unidade" value="${produto.unidade}" 
                       placeholder="Ex: kg, litro, unidade" required>
            </div>
            
            <div class="form-group">
                <label for="quantidadeEstoque">Quantidade em Estoque *</label>
                <input type="number" id="quantidadeEstoque" name="quantidadeEstoque" 
                       min="0" value="${produto.quantidadeEstoque}" required>
            </div>
            
            <div class="form-group">
                <label for="quantidadeMinima">Quantidade Mínima *</label>
                <input type="number" id="quantidadeMinima" name="quantidadeMinima" 
                       min="0" value="${produto.quantidadeMinima}" required>
            </div>
            
            <div class="form-group">
                <label for="quantidadeMaxima">Quantidade Máxima *</label>
                <input type="number" id="quantidadeMaxima" name="quantidadeMaxima" 
                       min="0" value="${produto.quantidadeMaxima}" required>
            </div>
            
            <div class="form-group">
                <label for="categoriaId">Categoria</label>
                <select id="categoriaId" name="categoriaId">
                    <option value="">Selecione uma categoria</option>
                    <c:forEach var="categoria" items="${categorias}">
                        <option value="${categoria.id}" 
                                ${produto != null && produto.categoriaId == categoria.id ? 'selected' : ''}>
                            ${categoria.nome}
                        </option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="text-right">
                <a href="produto?action=listar" class="btn btn-secondary">Cancelar</a>
                <button type="submit" class="btn btn-success">Salvar</button>
            </div>
        </form>
    </div>
    
    <script>
        function validarFormulario() {
            const quantidadeMinima = parseInt(document.getElementById('quantidadeMinima').value);
            const quantidadeMaxima = parseInt(document.getElementById('quantidadeMaxima').value);
            
            if (quantidadeMinima >= quantidadeMaxima) {
                alert('A quantidade mínima deve ser menor que a quantidade máxima!');
                return false;
            }
            
            return true;
        }
    </script>
</body>
</html>

