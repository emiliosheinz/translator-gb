package br.com.unisinos.tranlatorgb;

import java.util.List;

public class Dicionario {

    protected String palavra;
    protected List<String> definicoes;

    public Dicionario(String palavra, List<String> definicoes) {
        this.palavra = palavra;
        this.definicoes = definicoes;
    }

    public String getPalavra() {
        return palavra;
    }

    public List<String> getDefinicoes() {
        return definicoes;
    }

    public void setDefinicoes(List<String> definicoes) {
        this.definicoes = definicoes;
    }
}
