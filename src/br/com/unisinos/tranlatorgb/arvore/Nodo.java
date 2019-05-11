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

    public void setChave(String chave) {
        this.chave = chave;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setDireita(Nodo direita) {
        this.direita = direita;
    }
}
