package com.estoque.model;

import java.util.Date;

public class Movimentacao {
    private int id;
    private int produtoId;
    private String produtoNome;
    private Date dataMovimentacao;
    private int quantidade;
    private String tipo; // Entrada ou Saída
    
    public Movimentacao() {
    }
    
    public Movimentacao(int id, int produtoId, Date dataMovimentacao, int quantidade, String tipo) {
        this.id = id;
        this.produtoId = produtoId;
        this.dataMovimentacao = dataMovimentacao;
        this.quantidade = quantidade;
        this.tipo = tipo;
    }
    
    // Getters e Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getProdutoId() {
        return produtoId;
    }
    
    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }
    
    public String getProdutoNome() {
        return produtoNome;
    }
    
    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }
    
    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }
    
    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }
    
    public int getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

