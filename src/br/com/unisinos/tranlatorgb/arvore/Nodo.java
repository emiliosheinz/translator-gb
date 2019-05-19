package br.com.unisinos.tranlatorgb.arvore;

import br.com.unisinos.tranlatorgb.Dicionario;

public class Nodo {

    private Dicionario chave;
    private int balanceamento;
    private Nodo esquerda, direita;

    public Nodo(Dicionario chave) {
        this.chave = chave;
        this.balanceamento = 0;
        this.esquerda = new Nodo();
        this.direita = new Nodo();
    }

    public Nodo() {
    }

    public Dicionario getChave() {
        return chave;
    }


    public int getBalanceamento() {
        return balanceamento;
    }

    public Nodo getEsquerda() {
        return esquerda;
    }

    public Nodo getDireita() {
        return direita;
    }

    public void setEsquerda(Nodo esquerda) {
        this.esquerda = esquerda;
    }

    public void setChave(Dicionario chave) {
        this.chave = chave;
    }

    public void setBalanceamento(int balanceamento) {
        this.balanceamento = balanceamento;
    }

    public void setDireita(Nodo direita) {
        this.direita = direita;
    }
}
