package br.com.unisinos.tranlatorgb.arvore;

import br.com.unisinos.tranlatorgb.Dicionario;

public class Nodo {

    private Dicionario chave;
    private int altura;
    private Nodo esquerda, direita;

    public Nodo(Dicionario chave) {
        this.chave = chave;
        this.altura = 1;
    }

    public Dicionario getChave() {
        return chave;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public Nodo getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Nodo esquerda) {
        this.esquerda = esquerda;
    }

    public Nodo getDireita() {
        return direita;
    }

    public void setDireita(Nodo direita) {
        this.direita = direita;
    }
}
