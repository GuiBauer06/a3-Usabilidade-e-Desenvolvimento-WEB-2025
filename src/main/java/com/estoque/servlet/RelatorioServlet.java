package com.estoque.servlet;

import com.estoque.dao.EstoqueDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RelatorioServlet extends HttpServlet {
    private EstoqueDAO dao = new EstoqueDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipo = request.getParameter("tipo");
        
        try {
            switch (tipo) {
                case "lista-precos":
                    request.setAttribute("produtos", dao.listarProdutos());
                    request.getRequestDispatcher("/relatorio-lista-precos.jsp").forward(request, response);
                    break;
                    
                case "balanco":
                    List<com.estoque.model.Produto> produtos = dao.listarProdutos();
                    double valorTotal = produtos.stream()
                        .mapToDouble(p -> p.getPrecoUnitario() * p.getQuantidadeEstoque())
                        .sum();
                    request.setAttribute("produtos", produtos);
                    request.setAttribute("valorTotal", valorTotal);
                    request.getRequestDispatcher("/relatorio-balanco.jsp").forward(request, response);
                    break;
                    
                case "abaixo-minimo":
                    request.setAttribute("produtos", dao.produtosAbaixoMinimo());
                    request.getRequestDispatcher("/relatorio-abaixo-minimo.jsp").forward(request, response);
                    break;
                    
                case "por-categoria":
                    request.setAttribute("dados", dao.produtosPorCategoria());
                    request.getRequestDispatcher("/relatorio-por-categoria.jsp").forward(request, response);
                    break;
                    
                case "mais-movimentado":
                    request.setAttribute("maisSaida", dao.produtoMaisSaida());
                    request.setAttribute("maisEntrada", dao.produtoMaisEntrada());
                    request.getRequestDispatcher("/relatorio-mais-movimentado.jsp").forward(request, response);
                    break;
                    
                default:
                    request.getRequestDispatcher("/relatorios.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

