package com.example.doaoalimentos;

public class ItemDoacao {
    private String categoria;
    private String nomeItem;
    private int quantidade;

    public ItemDoacao(String categoria, String nomeItem, int quantidade) {
        this.categoria = categoria;
        this.nomeItem = nomeItem;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
