package com.estoque.servlet;

import com.estoque.dao.EstoqueDAO;
import com.estoque.model.Categoria;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/categoria")
public class CategoriaServlet extends HttpServlet {
    private EstoqueDAO dao = new EstoqueDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            if ("editar".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Categoria categoria = dao.buscarCategoria(id);
                request.setAttribute("categoria", categoria);
                request.getRequestDispatcher("/categoria-form.jsp").forward(request, response);
            } else if ("excluir".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.excluirCategoria(id);
                response.sendRedirect("categoria?action=listar");
            } else {
                request.setAttribute("categorias", dao.listarCategorias());
                request.getRequestDispatcher("/categoria-list.jsp").forward(request, response);
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
                Categoria categoria = new Categoria();
                
                String idStr = request.getParameter("id");
                if (idStr != null && !idStr.isEmpty()) {
                    categoria.setId(Integer.parseInt(idStr));
                }
                
                categoria.setNome(request.getParameter("nome"));
                categoria.setTamanho(request.getParameter("tamanho"));
                categoria.setEmbalagem(request.getParameter("embalagem"));
                
                if (categoria.getId() > 0) {
                    dao.atualizarCategoria(categoria);
                } else {
                    dao.inserirCategoria(categoria);
                }
                
                response.sendRedirect("categoria?action=listar");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

