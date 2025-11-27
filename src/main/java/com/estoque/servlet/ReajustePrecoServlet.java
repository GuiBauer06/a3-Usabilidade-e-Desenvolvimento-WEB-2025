package com.estoque.servlet;

import com.estoque.dao.EstoqueDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/reajuste-preco")
public class ReajustePrecoServlet extends HttpServlet {
    private EstoqueDAO dao = new EstoqueDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/reajuste-preco.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            double percentual = Double.parseDouble(request.getParameter("percentual"));
            dao.reajustarPrecos(percentual);
            request.setAttribute("mensagem", "Preços reajustados com sucesso!");
            request.getRequestDispatcher("/reajuste-preco.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

