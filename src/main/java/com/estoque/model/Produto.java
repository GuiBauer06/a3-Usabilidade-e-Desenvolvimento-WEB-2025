package com.estoque.model;

public class Produto {
    private int id;
    private String nome;
    private double precoUnitario;
    private String unidade;
    private int quantidadeEstoque;
    private int quantidadeMinima;
    private int quantidadeMaxima;
    private int categoriaId;
    private String categoriaNome;
    
    public Produto() {
    }
    
    public Produto(int id, String nome, double precoUnitario, String unidade, 
                   int quantidadeEstoque, int quantidadeMinima, int quantidadeMaxima, 
                   int categoriaId) {
        this.id = id;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.unidade = unidade;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeMinima = quantidadeMinima;
        this.quantidadeMaxima = quantidadeMaxima;
        this.categoriaId = categoriaId;
    }
    
    // Getters e Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getPrecoUnitario() {
        return precoUnitario;
    }
    
    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    
    public String getUnidade() {
        return unidade;
    }
    
    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
    
    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
    
    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
    
    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }
    
    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }
    
    public int getQuantidadeMaxima() {
        return quantidadeMaxima;
    }
    
    public void setQuantidadeMaxima(int quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }
    
    public int getCategoriaId() {
        return categoriaId;
    }
    
    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }
    
    public String getCategoriaNome() {
        return categoriaNome;
    }
    
    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }
    
    public double getValorTotal() {
        return precoUnitario * quantidadeEstoque;
    }
    
    public boolean isAbaixoMinimo() {
        return quantidadeEstoque < quantidadeMinima;
    }
    
    public boolean isAcimaMaximo() {
        return quantidadeEstoque > quantidadeMaxima;
    }
}

