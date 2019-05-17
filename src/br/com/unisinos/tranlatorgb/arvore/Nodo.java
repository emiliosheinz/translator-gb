package br.com.unisinos.tranlatorgb.arvore;

import br.com.unisinos.tranlatorgb.Dicionario;

public class Nodo {

    private Dicionario chave;
    private int altura;
    private Nodo esquerda, direita;

    public Nodo(Dicionario chave) {
        this.chave = chave;
        this.altura = 1;
        this.esquerda = new Nodo();
        this.direita = new Nodo();
    }

    public Nodo() { }

    public Dicionario getChave() {
        return chave;
    }


    public int getAltura() {
        return altura;
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

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setDireita(Nodo direita) {
        this.direita = direita;
    }
}
