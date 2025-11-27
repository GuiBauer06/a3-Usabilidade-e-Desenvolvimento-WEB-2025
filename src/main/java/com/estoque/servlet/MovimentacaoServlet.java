package com.estoque.servlet;

import com.estoque.dao.EstoqueDAO;
import com.estoque.model.Movimentacao;
import com.estoque.model.Produto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MovimentacaoServlet extends HttpServlet {
    private EstoqueDAO dao = new EstoqueDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("produtos", dao.listarProdutos());
            request.setAttribute("movimentacoes", dao.listarMovimentacoes());
            request.getRequestDispatcher("/movimentacao-form.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setProdutoId(Integer.parseInt(request.getParameter("produtoId")));
            
            String dataStr = request.getParameter("dataMovimentacao");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            movimentacao.setDataMovimentacao(sdf.parse(dataStr));
            
            movimentacao.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
            movimentacao.setTipo(request.getParameter("tipo"));
            
            dao.inserirMovimentacao(movimentacao);
            
            // Verificar alertas
            Produto produto = dao.buscarProduto(movimentacao.getProdutoId());
            String mensagem = "";
            if ("Saída".equals(movimentacao.getTipo()) && produto.isAbaixoMinimo()) {
                mensagem = "ATENÇÃO: A quantidade do produto está abaixo da quantidade mínima!";
            } else if ("Entrada".equals(movimentacao.getTipo()) && produto.isAcimaMaximo()) {
                mensagem = "ATENÇÃO: A quantidade do produto está acima da quantidade máxima!";
            }
            
            // Recarregar dados e mostrar alerta
            request.setAttribute("produtos", dao.listarProdutos());
            request.setAttribute("movimentacoes", dao.listarMovimentacoes());
            if (!mensagem.isEmpty()) {
                request.setAttribute("alerta", mensagem);
            }
            request.getRequestDispatcher("/movimentacao-form.jsp").forward(request, response);
        } catch (SQLException | ParseException e) {
            throw new ServletException(e);
        }
    }
}

