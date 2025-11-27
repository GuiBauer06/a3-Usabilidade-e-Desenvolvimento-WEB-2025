package com.estoque.servlet;

import com.estoque.dao.EstoqueDAO;
import com.estoque.model.Produto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ProdutoServlet extends HttpServlet {
    private EstoqueDAO dao = new EstoqueDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            if ("editar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Produto produto = dao.buscarProduto(id);
                request.setAttribute("produto", produto);
                request.setAttribute("categorias", dao.listarCategorias());
                request.getRequestDispatcher("/produto-form.jsp").forward(request, response);
            } else if ("excluir".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.excluirProduto(id);
                response.sendRedirect("produto?action=listar");
            } else {
                request.setAttribute("produtos", dao.listarProdutos());
                request.setAttribute("categorias", dao.listarCategorias());
                request.getRequestDispatcher("/produto-list.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            if ("salvar".equals(action)) {
                Produto produto = new Produto();
                
                String idStr = request.getParameter("id");
                if (idStr != null && !idStr.isEmpty()) {
                    produto.setId(Integer.parseInt(idStr));
                }
                
                produto.setNome(request.getParameter("nome"));
                produto.setPrecoUnitario(Double.parseDouble(request.getParameter("precoUnitario")));
                produto.setUnidade(request.getParameter("unidade"));
                produto.setQuantidadeEstoque(Integer.parseInt(request.getParameter("quantidadeEstoque")));
                produto.setQuantidadeMinima(Integer.parseInt(request.getParameter("quantidadeMinima")));
                produto.setQuantidadeMaxima(Integer.parseInt(request.getParameter("quantidadeMaxima")));
                
                String categoriaIdStr = request.getParameter("categoriaId");
                if (categoriaIdStr != null && !categoriaIdStr.isEmpty() && !categoriaIdStr.equals("0")) {
                    produto.setCategoriaId(Integer.parseInt(categoriaIdStr));
                } else {
                    produto.setCategoriaId(0); // 0 indica sem categoria
                }
                
                if (produto.getId() > 0) {
                    dao.atualizarProduto(produto);
                } else {
                    dao.inserirProduto(produto);
                }
                
                response.sendRedirect("produto?action=listar");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

