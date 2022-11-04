package com.example.appavaliacao2;

public class Historico {
    private double total, resultado;
    private int quantidade;

    public Historico(double total, double resultado, int quantidade){
        this.total = total;
        this.resultado = resultado;
        this.quantidade = quantidade;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
