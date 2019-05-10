package br.com.unisinos.tranlatorgb.arvore;

public class Nodo {

    private String chave;
    private int altura;
    private Nodo esquerda, direita;

    public Nodo(String chave){
        this.chave = chave;
        this.altura = 1;
    }

    public String getChave() {
        return chave;
    }
}
