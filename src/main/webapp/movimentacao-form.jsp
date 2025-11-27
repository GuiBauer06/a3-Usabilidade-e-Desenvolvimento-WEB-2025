<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Movimentações de Estoque</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>📊 Movimentações de Estoque</h1>
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
        
        <c:if test="${not empty alerta}">
            <div class="alert alert-warning">
                ${alerta}
            </div>
        </c:if>
        
        <h2>Nova Movimentação</h2>
        <form action="movimentacao" method="post" onsubmit="return validarMovimentacao()">
            <div class="form-group">
                <label for="produtoId">Produto *</label>
                <select id="produtoId" name="produtoId" required>
                    <option value="">Selecione um produto</option>
                    <c:forEach var="produto" items="${produtos}">
                        <option value="${produto.id}">${produto.nome} (Estoque: ${produto.quantidadeEstoque} ${produto.unidade})</option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="form-group">
                <label for="dataMovimentacao">Data da Movimentação *</label>
                <input type="date" id="dataMovimentacao" name="dataMovimentacao" 
                       value="<fmt:formatDate value='<%=new java.util.Date()%>' pattern='yyyy-MM-dd'/>" required>
            </div>
            
            <div class="form-group">
                <label for="quantidade">Quantidade *</label>
                <input type="number" id="quantidade" name="quantidade" min="1" required>
            </div>
            
            <div class="form-group">
                <label for="tipo">Tipo de Movimentação *</label>
                <select id="tipo" name="tipo" required>
                    <option value="">Selecione o tipo</option>
                    <option value="Entrada">Entrada</option>
                    <option value="Saída">Saída</option>
                </select>
            </div>
            
            <div class="text-right">
                <button type="submit" class="btn btn-success">Registrar Movimentação</button>
            </div>
        </form>
        
        <h2 class="mt-20">Histórico de Movimentações</h2>
        <c:if test="${not empty movimentacoes}">
            <table>
                <thead>
                    <tr>
                        <th>Data</th>
                        <th>Produto</th>
                        <th>Quantidade</th>
                        <th>Tipo</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="mov" items="${movimentacoes}">
                        <tr>
                            <td><fmt:formatDate value="${mov.dataMovimentacao}" pattern="dd/MM/yyyy"/></td>
                            <td>${mov.produtoNome}</td>
                            <td>${mov.quantidade}</td>
                            <td>
                                <span style="color: ${mov.tipo == 'Entrada' ? '#28a745' : '#dc3545'}; font-weight: bold;">
                                    ${mov.tipo}
                                </span>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        
        <c:if test="${empty movimentacoes}">
            <div class="empty-message">
                <p>Nenhuma movimentação registrada ainda.</p>
            </div>
        </c:if>
    </div>
    
    <script>
        function validarMovimentacao() {
            const tipo = document.getElementById('tipo').value;
            const quantidade = parseInt(document.getElementById('quantidade').value);
            const produtoSelect = document.getElementById('produtoId');
            const produtoTexto = produtoSelect.options[produtoSelect.selectedIndex].text;
            
            if (tipo === 'Saída') {
                // Extrair quantidade em estoque do texto da opção
                const match = produtoTexto.match(/Estoque: (\d+)/);
                if (match) {
                    const estoqueAtual = parseInt(match[1]);
                    if (quantidade > estoqueAtual) {
                        alert('A quantidade de saída não pode ser maior que a quantidade em estoque!');
                        return false;
                    }
                }
            }
            
            return true;
        }
    </script>
</body>
</html>

