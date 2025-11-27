package com.estoque.dao;

import com.estoque.model.Categoria;
import com.estoque.model.Movimentacao;
import com.estoque.model.Produto;
import com.estoque.util.MigrationRunner;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EstoqueDAO {
    private static final String URL = "jdbc:h2:mem:estoque;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    
    static {
        try {
            Class.forName("org.h2.Driver");
            // Executar migrations ao invés de criar tabelas diretamente
            MigrationRunner.runMigrations();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    // ========== CATEGORIAS ==========
    
    public void inserirCategoria(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO categorias (nome, tamanho, embalagem) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoria.getNome());
            pstmt.setString(2, categoria.getTamanho());
            pstmt.setString(3, categoria.getEmbalagem());
            pstmt.executeUpdate();
        }
    }
    
    public List<Categoria> listarCategorias() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categorias ORDER BY nome";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                categorias.add(new Categoria(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("tamanho"),
                    rs.getString("embalagem")
                ));
            }
        }
        return categorias;
    }
    
    public Categoria buscarCategoria(int id) throws SQLException {
        String sql = "SELECT * FROM categorias WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Categoria(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("tamanho"),
                        rs.getString("embalagem")
                    );
                }
            }
        }
        return null;
    }
    
    public void atualizarCategoria(Categoria categoria) throws SQLException {
        String sql = "UPDATE categorias SET nome = ?, tamanho = ?, embalagem = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoria.getNome());
            pstmt.setString(2, categoria.getTamanho());
            pstmt.setString(3, categoria.getEmbalagem());
            pstmt.setInt(4, categoria.getId());
            pstmt.executeUpdate();
        }
    }
    
    public void excluirCategoria(int id) throws SQLException {
        String sql = "DELETE FROM categorias WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    
    // ========== PRODUTOS ==========
    
    public void inserirProduto(Produto produto) throws SQLException {
        String sql = "INSERT INTO produtos (nome, preco_unitario, unidade, quantidade_estoque, " +
                     "quantidade_minima, quantidade_maxima, categoria_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setDouble(2, produto.getPrecoUnitario());
            pstmt.setString(3, produto.getUnidade());
            pstmt.setInt(4, produto.getQuantidadeEstoque());
            pstmt.setInt(5, produto.getQuantidadeMinima());
            pstmt.setInt(6, produto.getQuantidadeMaxima());
            // Se categoriaId for 0, usar NULL
            if (produto.getCategoriaId() > 0) {
                pstmt.setInt(7, produto.getCategoriaId());
            } else {
                pstmt.setNull(7, java.sql.Types.INTEGER);
            }
            pstmt.executeUpdate();
        }
    }
    
    public List<Produto> listarProdutos() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT p.*, c.nome as categoria_nome FROM produtos p " +
                     "LEFT JOIN categorias c ON p.categoria_id = c.id ORDER BY p.nome";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("preco_unitario"),
                    rs.getString("unidade"),
                    rs.getInt("quantidade_estoque"),
                    rs.getInt("quantidade_minima"),
                    rs.getInt("quantidade_maxima"),
                    rs.getInt("categoria_id")
                );
                produto.setCategoriaNome(rs.getString("categoria_nome"));
                produtos.add(produto);
            }
        }
        return produtos;
    }
    
    public Produto buscarProduto(int id) throws SQLException {
        String sql = "SELECT p.*, c.nome as categoria_nome FROM produtos p " +
                     "LEFT JOIN categorias c ON p.categoria_id = c.id WHERE p.id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco_unitario"),
                        rs.getString("unidade"),
                        rs.getInt("quantidade_estoque"),
                        rs.getInt("quantidade_minima"),
                        rs.getInt("quantidade_maxima"),
                        rs.getInt("categoria_id")
                    );
                    produto.setCategoriaNome(rs.getString("categoria_nome"));
                    return produto;
                }
            }
        }
        return null;
    }
    
    public void atualizarProduto(Produto produto) throws SQLException {
        String sql = "UPDATE produtos SET nome = ?, preco_unitario = ?, unidade = ?, " +
                     "quantidade_estoque = ?, quantidade_minima = ?, quantidade_maxima = ?, " +
                     "categoria_id = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setDouble(2, produto.getPrecoUnitario());
            pstmt.setString(3, produto.getUnidade());
            pstmt.setInt(4, produto.getQuantidadeEstoque());
            pstmt.setInt(5, produto.getQuantidadeMinima());
            pstmt.setInt(6, produto.getQuantidadeMaxima());
            // Se categoriaId for 0, usar NULL
            if (produto.getCategoriaId() > 0) {
                pstmt.setInt(7, produto.getCategoriaId());
            } else {
                pstmt.setNull(7, java.sql.Types.INTEGER);
            }
            pstmt.setInt(8, produto.getId());
            pstmt.executeUpdate();
        }
    }
    
    public void excluirProduto(int id) throws SQLException {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    
    public void reajustarPrecos(double percentual) throws SQLException {
        String sql = "UPDATE produtos SET preco_unitario = preco_unitario * (1 + ? / 100)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, percentual);
            pstmt.executeUpdate();
        }
    }
    
    // ========== MOVIMENTAÇÕES ==========
    
    public void inserirMovimentacao(Movimentacao movimentacao) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        try {
            conn.setAutoCommit(false);
            
            // Inserir movimentação
            String sqlMov = "INSERT INTO movimentacoes (produto_id, data_movimentacao, quantidade, tipo) " +
                           "VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlMov)) {
                pstmt.setInt(1, movimentacao.getProdutoId());
                pstmt.setDate(2, new java.sql.Date(movimentacao.getDataMovimentacao().getTime()));
                pstmt.setInt(3, movimentacao.getQuantidade());
                pstmt.setString(4, movimentacao.getTipo());
                pstmt.executeUpdate();
            }
            
            // Atualizar estoque do produto
            String tipo = movimentacao.getTipo() != null ? movimentacao.getTipo().trim() : "";
            String sqlProd;
            
            // Verificar se é saída (com ou sem acento)
            if (tipo.equalsIgnoreCase("Saída") || tipo.equalsIgnoreCase("Saida") || tipo.equalsIgnoreCase("Saida")) {
                // Saída: diminuir a quantidade do estoque
                sqlProd = "UPDATE produtos SET quantidade_estoque = quantidade_estoque - ? WHERE id = ?";
            } else {
                // Entrada: aumentar a quantidade do estoque
                sqlProd = "UPDATE produtos SET quantidade_estoque = quantidade_estoque + ? WHERE id = ?";
            }
            
            try (PreparedStatement pstmt = conn.prepareStatement(sqlProd)) {
                pstmt.setInt(1, movimentacao.getQuantidade());
                pstmt.setInt(2, movimentacao.getProdutoId());
                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated == 0) {
                    throw new SQLException("Produto não encontrado para atualização");
                }
            }
            
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }
    
    public List<Movimentacao> listarMovimentacoes() throws SQLException {
        List<Movimentacao> movimentacoes = new ArrayList<>();
        String sql = "SELECT m.*, p.nome as produto_nome FROM movimentacoes m " +
                     "JOIN produtos p ON m.produto_id = p.id " +
                     "ORDER BY m.data_movimentacao DESC, m.id DESC";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Movimentacao mov = new Movimentacao(
                    rs.getInt("id"),
                    rs.getInt("produto_id"),
                    rs.getDate("data_movimentacao"),
                    rs.getInt("quantidade"),
                    rs.getString("tipo")
                );
                mov.setProdutoNome(rs.getString("produto_nome"));
                movimentacoes.add(mov);
            }
        }
        return movimentacoes;
    }
    
    // ========== RELATÓRIOS ==========
    
    public List<Produto> produtosAbaixoMinimo() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT p.*, c.nome as categoria_nome FROM produtos p " +
                     "LEFT JOIN categorias c ON p.categoria_id = c.id " +
                     "WHERE p.quantidade_estoque < p.quantidade_minima ORDER BY p.nome";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("preco_unitario"),
                    rs.getString("unidade"),
                    rs.getInt("quantidade_estoque"),
                    rs.getInt("quantidade_minima"),
                    rs.getInt("quantidade_maxima"),
                    rs.getInt("categoria_id")
                );
                produto.setCategoriaNome(rs.getString("categoria_nome"));
                produtos.add(produto);
            }
        }
        return produtos;
    }
    
    public List<Object[]> produtosPorCategoria() throws SQLException {
        List<Object[]> resultado = new ArrayList<>();
        String sql = "SELECT c.nome as categoria, COUNT(p.id) as quantidade " +
                     "FROM categorias c LEFT JOIN produtos p ON c.id = p.categoria_id " +
                     "GROUP BY c.id, c.nome ORDER BY c.nome";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                resultado.add(new Object[]{
                    rs.getString("categoria"),
                    rs.getInt("quantidade")
                });
            }
        }
        return resultado;
    }
    
    public Object[] produtoMaisSaida() throws SQLException {
        String sql = "SELECT p.nome, SUM(m.quantidade) as total_saida " +
                     "FROM movimentacoes m JOIN produtos p ON m.produto_id = p.id " +
                     "WHERE m.tipo = 'Saída' " +
                     "GROUP BY p.id, p.nome " +
                     "ORDER BY total_saida DESC LIMIT 1";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return new Object[]{rs.getString("nome"), rs.getInt("total_saida")};
            }
        }
        return null;
    }
    
    public Object[] produtoMaisEntrada() throws SQLException {
        String sql = "SELECT p.nome, SUM(m.quantidade) as total_entrada " +
                     "FROM movimentacoes m JOIN produtos p ON m.produto_id = p.id " +
                     "WHERE m.tipo = 'Entrada' " +
                     "GROUP BY p.id, p.nome " +
                     "ORDER BY total_entrada DESC LIMIT 1";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return new Object[]{rs.getString("nome"), rs.getInt("total_entrada")};
            }
        }
        return null;
    }
}

